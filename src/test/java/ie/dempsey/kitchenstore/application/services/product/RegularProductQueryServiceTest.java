package ie.dempsey.kitchenstore.application.services.product;

import ie.dempsey.kitchenstore.application.exceptions.NoSuchHouseException;
import ie.dempsey.kitchenstore.application.exceptions.NoSuchProductException;
import ie.dempsey.kitchenstore.domain.entities.House;
import ie.dempsey.kitchenstore.domain.entities.Product;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class RegularProductQueryServiceTest {
    public static final long TOTAL_PRODUCT_COUNT = 15;

    private TestEntityFactory entities;

    @Mock
    ProductRepository mockProductRepo;
    @Mock
    HouseRepository mockHouseRepo;
    @Mock
    ProductRepository mockEmptyProductRepo;
    RegularProductQueryService regularService;
    RegularProductQueryService emptyService;

    private static void createRelationships(List<Product> products, List<House> houses) {
        List<Product> fridgeProducts = products.subList(0, 2);
        fridgeProducts.forEach(product -> product.setHouse(houses.get(0)));
        houses.get(0).setProducts(fridgeProducts);

        List<Product> cupboardProducts = products.subList(2, 4);
        cupboardProducts.forEach(product -> product.setHouse(houses.get(1)));
        houses.get(1).setProducts(cupboardProducts);
    }

    private void initializeTestObjects() {
        entities = new TestEntityFactory();
        createRelationships(entities.products(), entities.houses());
        initializeMockRepos();
        regularService = new RegularProductQueryService(mockProductRepo, mockHouseRepo);
        emptyService = new RegularProductQueryService(mockEmptyProductRepo, mockHouseRepo);
    }

    // todo create a util class to do this for all tests
    private void initializeMockRepos() {
        when(mockProductRepo.findAll()).thenReturn(entities.products());
        when(mockEmptyProductRepo.findAll()).thenReturn(new ArrayList<>());
        when(mockProductRepo.findById(TestEntityFactory.STEAK_ID))
                .thenReturn(Optional.ofNullable(entities.steak()));

        when(mockHouseRepo.findAll()).thenReturn(entities.houses());

        // count of products will be the sum of their quantities
        when(mockProductRepo.count()).thenReturn(TOTAL_PRODUCT_COUNT);
        when(mockEmptyProductRepo.count()).thenReturn(0L);

        when(mockHouseRepo.findById(TestEntityFactory.CUPBOARD_ID))
                .thenReturn(java.util.Optional.ofNullable(entities.cupboard()));
        when(mockHouseRepo.findById(TestEntityFactory.REFRIGERATOR_ID))
                .thenReturn(java.util.Optional.ofNullable(entities.refrigerator()));
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        initializeTestObjects();
    }

    @AfterEach
    void tearDown() {
        entities = null;
        mockProductRepo = null;
        mockHouseRepo = null;
        mockEmptyProductRepo = null;
        regularService = null;
        emptyService = null;
    }

    @Test
    void getAll() {
        List<Product> products = regularService.getAll();

        assertIterableEquals(entities.products(), products);
        Mockito.verify(mockProductRepo).findAll();
    }

    @Test
    void getAll_empty() {
        List<Product> products = emptyService.getAll();
        assertTrue(products.isEmpty());
    }

    @Test
    void getFromHouse() throws NoSuchHouseException {
        List<Product> cupboardProducts = entities.products().stream()
                .filter(p -> p.getHouse() == entities.cupboard())
                .collect(Collectors.toList());
        List<Product> products = regularService.getFromHouse(TestEntityFactory.CUPBOARD_ID);

        assertIterableEquals(cupboardProducts, products);
    }

    @Test
    void getFromHouse_NonExistent() {
        long nonExistentHouseId = TestEntityFactory.NON_EXISTENT_ID;

        NoSuchHouseException exception = assertThrows(
                NoSuchHouseException.class, () -> regularService.getFromHouse(nonExistentHouseId));
        assertEquals("House with id=400 does not exist.", exception.getMessage());
    }

    @Test
    void getWithName() {
        List<Product> lemonadeProducts = regularService.getWithName("Lemonade");

        assertTrue(lemonadeProducts.stream().allMatch(p -> p.getName().equalsIgnoreCase("Lemonade")));
        assertTrue(lemonadeProducts.stream().anyMatch(p -> p.getHouse().equals(entities.refrigerator())));
        assertTrue(lemonadeProducts.stream().anyMatch(p -> p.getHouse().equals(entities.cupboard())));
    }

    @Test
    void getWithName_fromHouse() throws NoSuchHouseException {
        List<Product> lemonadeProducts = regularService.getWithName("Lemonade", TestEntityFactory.REFRIGERATOR_ID);
        assertTrue(lemonadeProducts.stream().allMatch(p -> p.getHouse().equals(entities.refrigerator())));
    }

    @Test
    void getWithName_fromHouse_productDoesNotExist() throws NoSuchHouseException {
        List<Product> cerealProducts = regularService.getWithName("Cereal", TestEntityFactory.REFRIGERATOR_ID);
        assertTrue(cerealProducts.isEmpty());
    }

    @Test
    void getById() throws NoSuchProductException {
        Product steak = regularService.getById(TestEntityFactory.STEAK_ID);
        assertEquals(entities.steak(), steak);
    }

    @Test
    void getById_nonExistent() {
        NoSuchProductException exception = assertThrows(
                NoSuchProductException.class, () -> regularService.getById(TestEntityFactory.NON_EXISTENT_ID));
        assertEquals("Product with id=400 does not exist", exception.getMessage());
    }

    @Test
    void countAll() {
        long countOfProducts = regularService.countAll();

        assertEquals(TOTAL_PRODUCT_COUNT, countOfProducts);
        Mockito.verify(mockProductRepo).count();
    }

    @Test
    void countAll_empty() {
        long countOfProducts = emptyService.countAll();
        assertEquals(0, countOfProducts);
    }
}