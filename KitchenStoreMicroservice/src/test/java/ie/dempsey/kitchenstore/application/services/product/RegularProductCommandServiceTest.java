package ie.dempsey.kitchenstore.application.services.product;

import ie.dempsey.kitchenstore.domain.entities.House;
import ie.dempsey.kitchenstore.domain.entities.Product;
import ie.dempsey.kitchenstore.domain.entities.tags.Tag;
import ie.dempsey.kitchenstore.infrastructure.repositories.HouseRepository;
import ie.dempsey.kitchenstore.infrastructure.repositories.ProductRepository;
import ie.dempsey.kitchenstore.testutil.TestEntityFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RegularProductCommandServiceTest {
    @Mock
    ProductRepository mockProductRepo;
    @Mock
    HouseRepository mockHouseRepo;

    Tag[] tags = TestEntityFactory.TAGS;
    RegularProductCommandService service;
    private TestEntityFactory entities;
    private House sourceHouse;
    private House destinationHouse;
    private Product lemonade;
    private Product steak;
    private Product cereal;

    private void initializeTestObjects() {
        service = new RegularProductCommandService(mockProductRepo, mockHouseRepo);
        entities = new TestEntityFactory();

        sourceHouse = entities.cupboard();
        destinationHouse = entities.refrigerator();

        lemonade = entities.cupboardLemonade();
        steak = entities.steak();
        cereal = entities.cereal();
    }

    private void createRelationships() {
        cereal.setHouse(sourceHouse);
        sourceHouse.getProducts().add(cereal);

        lemonade.setHouse(sourceHouse);
        sourceHouse.getProducts().add(lemonade);

        // product to add contains its house
        steak.setHouse(destinationHouse);
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        initializeTestObjects();
        createRelationships();
    }

    @AfterEach
    void tearDown() {
        mockProductRepo = null;
        mockHouseRepo = null;
        service = null;
    }

    @Test
    void add() {
        service.add(steak);

        assertTrue(destinationHouse.getProducts().contains(steak));
        assertEquals(destinationHouse, steak.getHouse());

        // verify that the correct methods were called on the repos
        Mockito.verify(mockHouseRepo).save(destinationHouse);
        Mockito.verify(mockProductRepo).save(steak);
    }

    @Test
    void remove() {
        service.remove(cereal);

        assertFalse(sourceHouse.getProducts().contains(cereal));
        assertNull(cereal.getHouse());

        Mockito.verify(mockHouseRepo).save(sourceHouse);
        Mockito.verify(mockProductRepo).delete(cereal);
    }

    @Test
    void update() {
        cereal.setDescription("Crunchy corn flakes");
        service.update(cereal);

        Mockito.verify(mockProductRepo).save(cereal);
    }

    @Test
    void moveTo() {
        assertEquals(sourceHouse, lemonade.getHouse());
        assertTrue(sourceHouse.getProducts().contains(lemonade));
        assertFalse(destinationHouse.getProducts().contains(lemonade));

        service.moveTo(lemonade, destinationHouse);

        assertEquals(destinationHouse, lemonade.getHouse());
        assertTrue(destinationHouse.getProducts().contains(lemonade));
        assertFalse(sourceHouse.getProducts().contains(lemonade));

        Mockito.verify(mockProductRepo).save(lemonade);
        Mockito.verify(mockHouseRepo).save(sourceHouse);
        Mockito.verify(mockHouseRepo).save(destinationHouse);
    }

    @Test
    void tag() {
        assertTrue(lemonade.getTags().isEmpty());

        // Do the tagging both ways to verify that varargs work
        service.tag(lemonade, tags);
        service.tag(lemonade, tags[0], tags[1]);

        assertEquals(2, lemonade.getTags().size());
        assertTrue(lemonade.getTags().contains(tags[0]));
        assertTrue(lemonade.getTags().contains(tags[1]));

        Mockito.verify(mockProductRepo, Mockito.times(2)).save(lemonade);
    }

    @Test
    void makeGhost() {
        // todo implement this when the 'Ghost' concept has been developed
    }
}