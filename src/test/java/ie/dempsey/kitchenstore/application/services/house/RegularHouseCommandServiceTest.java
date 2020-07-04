package ie.dempsey.kitchenstore.application.services.house;

import ie.dempsey.kitchenstore.domain.entities.House;
import ie.dempsey.kitchenstore.domain.entities.User;
import ie.dempsey.kitchenstore.infrastructure.repositories.HouseRepository;
import ie.dempsey.kitchenstore.infrastructure.repositories.UserRepository;
import ie.dempsey.kitchenstore.testutil.TestHouseFactory;
import ie.dempsey.kitchenstore.testutil.TestUserFactory;
import ie.dempsey.kitchenstore.testutil.TestingMocks;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class RegularHouseCommandServiceTest {
    HouseRepository mockHouseRepo;
    UserRepository mockUserRepo;

    RegularHouseCommandService testService;

    House cubby;
    User mary, jim;

    void initializeTestObjects() {
        mockHouseRepo = TestingMocks.mockHouseRepository();
        mockUserRepo = TestingMocks.mockUserRepository();
        testService = new RegularHouseCommandService(mockHouseRepo, mockUserRepo);

        cubby = TestHouseFactory.cupboard();
        mary = TestUserFactory.mary();
        jim = TestUserFactory.jim();
        cubby.getUsers().addAll(List.of(mary, jim));
    }

    @BeforeEach
    void setUp() {
        initializeTestObjects();
    }

    @AfterEach
    void tearDown() {
        mockHouseRepo = null;
        testService = null;
    }

    @Test
    void add_repositorySavesHouse() {
        testService.add(cubby);

        Mockito.verify(mockHouseRepo).save(cubby);
    }

    @Test
    void add_repositorySavesAllUsers() {
        testService.add(cubby);

        Mockito.verify(mockUserRepo).save(mary);
        Mockito.verify(mockUserRepo).save(jim);
    }

    @Test
    void add_usersReferenceHouse() {
        testService.add(cubby);

        assertTrue(mary.getHouses().contains(cubby));
        assertTrue(jim.getHouses().contains(cubby));
    }

    @Test
    void add_fail_invalidHouse() {
        // todo implement
    }

    @Test
    void remove() {
        // todo implement these
    }

    @Test
    void update() {
        // todo implement these
    }
}