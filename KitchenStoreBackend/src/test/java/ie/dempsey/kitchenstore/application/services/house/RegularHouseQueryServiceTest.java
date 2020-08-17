package ie.dempsey.kitchenstore.application.services.house;

import ie.dempsey.kitchenstore.application.exceptions.NoSuchHouseException;
import ie.dempsey.kitchenstore.domain.entities.House;
import ie.dempsey.kitchenstore.infrastructure.repositories.HouseRepository;
import ie.dempsey.kitchenstore.testutil.TestHouseFactory;
import ie.dempsey.kitchenstore.testutil.TestingMocks;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Example;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RegularHouseQueryServiceTest {
    HouseRepository mockHouseRepo;

    RegularHouseQueryService testService;
    RegularHouseQueryService emptyService;

    @BeforeEach
    void setUp() {
        initializeMocks();
        testService = new RegularHouseQueryService(mockHouseRepo);
    }

    private void initializeMocks() {
        mockHouseRepo = TestingMocks.mockHouseRepository();
    }

    @AfterEach
    void tearDown() {
        mockHouseRepo = null;
        testService = null;
        emptyService = null;
    }

    @Test
    void getById_exists() throws NoSuchHouseException {
        House houseWithId = testService.getById(TestHouseFactory.FRIDGE_ID);

        assertEquals(TestHouseFactory.FRIDGE_ID, houseWithId.getId());
        Mockito.verify(mockHouseRepo).findById(TestHouseFactory.FRIDGE_ID);
    }

    @Test
    void getById_doesNotExist() {
        assertThrows(NoSuchHouseException.class,
                () -> testService.getById(TestHouseFactory.NON_EXISTENT_HOUSE_ID)
        );
    }

    @Test
    void getAll() {
        List<House> houses = testService.getAll();

        assertIterableEquals(TestingMocks.allHouses, houses);
        Mockito.verify(mockHouseRepo).findAll();
    }

    @Test
    void getWithName_RegularNameExists() {
        List<House> housesWithName = testService.getWithName("");

        assertFalse(housesWithName.isEmpty());
        Example<House> anyExampleHouse = Mockito.any();
        Mockito.verify(mockHouseRepo).findAll(anyExampleHouse);
    }
}
