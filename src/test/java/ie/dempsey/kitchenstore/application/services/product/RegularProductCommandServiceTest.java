package ie.dempsey.kitchenstore.application.services.product;

import ie.dempsey.kitchenstore.domain.entities.House;
import ie.dempsey.kitchenstore.domain.entities.Product;
import ie.dempsey.kitchenstore.domain.entities.tags.Tag;
import ie.dempsey.kitchenstore.infrastructure.repositories.HouseRepository;
import ie.dempsey.kitchenstore.infrastructure.repositories.ProductRepository;
import ie.dempsey.kitchenstore.testutil.TestingEntities;
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

    // already contains cereal and lemonade
    House sourceHouse = TestingEntities.CUPBOARD;
    House destinationHouse = TestingEntities.REFRIGERATOR;

    Product lemonade = TestingEntities.LEMONADE;
    Product steak = TestingEntities.STEAK;
    Product cereal = TestingEntities.CEREAL;

    Tag[] tags = TestingEntities.TAGS;

    RegularProductCommandService testService;

    private void initializeTestObjects() {
        testService = new RegularProductCommandService(mockProductRepo, mockHouseRepo);
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        initializeTestObjects();
    }

    @AfterEach
    void tearDown() {
        mockProductRepo = null;
        mockHouseRepo = null;
        testService = null;
    }

    @Test
    void add() {
        testService.add(destinationHouse, steak);

        assertTrue(destinationHouse.getProducts().contains(steak));
        assertEquals(destinationHouse, steak.getHouse());

        // verify that the correct methods were called on the repos
        Mockito.verify(mockHouseRepo).save(destinationHouse);
        Mockito.verify(mockProductRepo).save(steak);
    }

    @Test
    void remove() {
        testService.remove(sourceHouse, cereal);

        assertFalse(sourceHouse.getProducts().contains(cereal));
        assertNull(cereal.getHouse());

        Mockito.verify(mockHouseRepo).save(sourceHouse);
        Mockito.verify(mockProductRepo).delete(cereal);
    }

    @Test
    void setQuantity() {
        assertEquals(2, lemonade.getQuantity());

        testService.setQuantity(lemonade, 3);

        assertEquals(3, lemonade.getQuantity());
        Mockito.verify(mockProductRepo).save(lemonade);
    }

    @Test
    void moveTo() {
        assertEquals(sourceHouse, lemonade.getHouse());
        assertTrue(sourceHouse.getProducts().contains(lemonade));
        assertFalse(destinationHouse.getProducts().contains(lemonade));

        testService.moveTo(lemonade, sourceHouse, destinationHouse);

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
        testService.tag(lemonade, tags);
        testService.tag(lemonade, tags[0], tags[1]);

        assertEquals(2, lemonade.getTags().size());
        assertTrue(lemonade.getTags().contains(tags[0]));
        assertTrue(lemonade.getTags().contains(tags[1]));

        Mockito.verify(mockProductRepo).save(lemonade);
    }

    @Test
    void makeGhost() {
        // todo implement this when the 'Ghost' concept has been developed
    }
}