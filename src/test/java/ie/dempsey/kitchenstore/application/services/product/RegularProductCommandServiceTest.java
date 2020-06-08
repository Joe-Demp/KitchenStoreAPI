package ie.dempsey.kitchenstore.application.services.product;

import ie.dempsey.kitchenstore.domain.entities.House;
import ie.dempsey.kitchenstore.domain.entities.Product;
import ie.dempsey.kitchenstore.infrastructure.repositories.HouseRepository;
import ie.dempsey.kitchenstore.infrastructure.repositories.ProductRepository;
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

    House testSourceHouse;
    House testDestinationHouse;
    Product testLemonade;
    Product testCereal;
    RegularProductCommandService testService;

    private void initializeTestObjects() {
        testSourceHouse = new House();    // todo initialize with products
        testDestinationHouse = new House();    // todo initialize with products
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
        testService.add(testDestinationHouse, testLemonade);

        // todo check who is responsible for this: The framework or me?
        assertTrue(testDestinationHouse.getProducts().contains(testLemonade));
        assertEquals(testDestinationHouse, testLemonade.getHouse());

        // verify that the correct methods were called on the repos
        Mockito.verify(mockHouseRepo).save(testDestinationHouse);
        Mockito.verify(mockProductRepo).save(testLemonade);
    }

    @Test
    void remove() {
        fail("Not yet implemented.");
    }

    @Test
    void setQuantity() {
        fail("Not yet implemented.");
    }

    @Test
    void moveTo() {
        fail("Not yet implemented.");
    }

    @Test
    void tag() {
        fail("Not yet implemented.");
    }

    @Test
    void makeGhost() {
        fail("Not yet implemented.");
    }
}