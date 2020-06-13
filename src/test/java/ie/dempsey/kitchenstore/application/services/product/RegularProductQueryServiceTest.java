package ie.dempsey.kitchenstore.application.services.product;

import ie.dempsey.kitchenstore.domain.entities.House;
import ie.dempsey.kitchenstore.domain.entities.Product;
import ie.dempsey.kitchenstore.infrastructure.repositories.HouseRepository;
import ie.dempsey.kitchenstore.infrastructure.repositories.ProductRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
class RegularProductQueryServiceTest {
    private static List<Product> productList;
    private static List<House> houseList;

    static {
        productList = new ArrayList<>();
        houseList = new ArrayList<>();
    }

    @Mock
    ProductRepository mockProductRepo;
    @Mock
    HouseRepository mockHouseRepo;
    @Mock
    ProductRepository mockEmptyProductRepo;
    @Mock
    HouseRepository mockEmptyHouseRepo;
    RegularProductQueryService regularService;
    RegularProductQueryService emptyService;

    private void initializeTestObjects() {
        initializeMockRepos();
        regularService = new RegularProductQueryService(mockProductRepo, mockHouseRepo);
    }

    private void initializeMockRepos() {
        when(mockProductRepo.findAll()).thenReturn(productList);
        when(mockEmptyProductRepo.findAll()).thenReturn(new ArrayList<>());

        when(mockHouseRepo.findAll()).thenReturn(houseList);
        when(mockEmptyHouseRepo.findAll()).thenReturn(new ArrayList<>());

        // count of products will be the sum of their quantities
        when(mockProductRepo.count()).thenReturn(7L);
        when(mockEmptyProductRepo.count()).thenReturn(0L);
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
        mockEmptyHouseRepo = null;
        regularService = null;
        emptyService = null;
    }

    @Test
    void getProducts_empty() {
    }

    @Test
    void getProducts() {

    }

    @Test
    void countProducts() {

    }

    @Test
    void countProducts_empty() {
    }
}