package ie.dempsey.kitchenstore.application.services.product;

import ie.dempsey.kitchenstore.application.exceptions.NoSuchHouseException;
import ie.dempsey.kitchenstore.application.exceptions.NoSuchProductException;
import ie.dempsey.kitchenstore.domain.entities.House;
import ie.dempsey.kitchenstore.domain.entities.Product;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class RegularProductQueryServiceTest {
    public static final long TOTAL_PRODUCT_COUNT = 15;
    public static final long PRODUCTS_IN_CUPBOARD = 3;

    private static List<Product> productList;
    private static List<House> houseList;

    static {
        productList = Arrays.asList(
                TestingEntities.LEMONADE,
                TestingEntities.CEREAL,
                TestingEntities.STEAK,
                TestingEntities.F_LEMONADE
        );

        List<House> houses = Arrays.asList(TestingEntities.CUPBOARD, TestingEntities.REFRIGERATOR);
        houseList = new ArrayList<>(houses);
    }

    @Mock
    ProductRepository mockProductRepo;
    @Mock
    HouseRepository mockHouseRepo;
    @Mock
    ProductRepository mockEmptyProductRepo;
    RegularProductQueryService regularService;
    RegularProductQueryService emptyService;

    private void initializeTestObjects() {
        initializeMockRepos();
        regularService = new RegularProductQueryService(mockProductRepo, mockHouseRepo);
        emptyService = new RegularProductQueryService(mockEmptyProductRepo, mockHouseRepo);
    }

    // todo create a util class to do this for all tests
    private void initializeMockRepos() {
        when(mockProductRepo.findAll()).thenReturn(productList);
        when(mockEmptyProductRepo.findAll()).thenReturn(new ArrayList<>());
        when(mockProductRepo.findById(TestingEntities.STEAK_ID)).thenReturn(Optional.ofNullable(TestingEntities.STEAK));

        when(mockHouseRepo.findAll()).thenReturn(houseList);

        // count of products will be the sum of their quantities
        when(mockProductRepo.count()).thenReturn(TOTAL_PRODUCT_COUNT);
        when(mockEmptyProductRepo.count()).thenReturn(0L);

        when(mockHouseRepo.findById(TestingEntities.CUPBOARD_ID))
                .thenReturn(java.util.Optional.ofNullable(TestingEntities.CUPBOARD));
        when(mockHouseRepo.findById(TestingEntities.REFRIGERATOR_ID))
                .thenReturn(java.util.Optional.ofNullable(TestingEntities.REFRIGERATOR));
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
        mockEmptyProductRepo = null;
        regularService = null;
        emptyService = null;
    }

    @Test
    void getAll() {
        List<Product> products = regularService.getAll();

        assertIterableEquals(productList, products);
        Mockito.verify(mockProductRepo).findAll();
    }

    @Test
    void getAll_empty() {
        List<Product> products = emptyService.getAll();
        assertTrue(products.isEmpty());
    }

    @Test
    void getFromHouse() throws NoSuchHouseException {
        List<Product> cupboardProducts = productList.stream()
                .filter(p -> p.getHouse() == TestingEntities.CUPBOARD)
                .collect(Collectors.toList());
        List<Product> products = regularService.getFromHouse(TestingEntities.CUPBOARD);

        assertIterableEquals(cupboardProducts, products);
    }

    @Test
    void getFromHouse_NonExistent() {
        House someNewHouse = new House().setId(TestingEntities.NON_EXISTENT_ID);

        NoSuchHouseException exception = assertThrows(
                NoSuchHouseException.class, () -> regularService.getFromHouse(someNewHouse));
        assertEquals("House with id=400 does not exist.", exception.getMessage());
    }

    @Test
    void getWithName() {
        List<Product> lemonadeProducts = regularService.getWithName("Lemonade");

        assertTrue(lemonadeProducts.stream().allMatch(p -> p.getName().equalsIgnoreCase("Lemonade")));
        assertTrue(lemonadeProducts.stream().anyMatch(p -> p.getHouse().equals(TestingEntities.REFRIGERATOR)));
        assertTrue(lemonadeProducts.stream().anyMatch(p -> p.getHouse().equals(TestingEntities.CUPBOARD)));
    }

    @Test
    void getWithName_fromHouse() throws NoSuchHouseException {
        List<Product> lemonadeProducts = regularService.getWithName("Lemonade", TestingEntities.REFRIGERATOR);
        assertTrue(lemonadeProducts.stream().allMatch(p -> p.getHouse().equals(TestingEntities.REFRIGERATOR)));
    }

    @Test
    void getWithName_fromHouse_productDoesNotExist() throws NoSuchHouseException {
        List<Product> cerealProducts = regularService.getWithName("Cereal", TestingEntities.REFRIGERATOR);
        assertTrue(cerealProducts.isEmpty());
    }

    @Test
    void getById() throws NoSuchProductException {
        Product steak = regularService.getById(TestingEntities.STEAK_ID);
        assertEquals(TestingEntities.STEAK, steak);
    }

    @Test
    void getById_nonExistent() {
        NoSuchProductException exception = assertThrows(
                NoSuchProductException.class, () -> regularService.getById(TestingEntities.NON_EXISTENT_ID));
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