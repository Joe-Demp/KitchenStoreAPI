package ie.dempsey.kitchenstore.application.services.house;

import ie.dempsey.kitchenstore.application.exceptions.ValidationException;
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
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

class RegularHouseCommandServiceTest {
    HouseRepository mockHouseRepo;
    UserRepository mockUserRepo;

    RegularHouseCommandService testService;

    House cubby, refrigerator;
    House invalidHouse;
    User mary, jim;

    void initializeTestObjects() {
        mockHouseRepo = TestingMocks.mockHouseRepository();
        mockUserRepo = TestingMocks.mockUserRepository();
        testService = new RegularHouseCommandService(mockHouseRepo, mockUserRepo);

        cubby = TestHouseFactory.cupboard();
        refrigerator = TestHouseFactory.fridge();
        mary = TestUserFactory.mary();
        jim = TestUserFactory.jim();
        cubby.getUsers().addAll(List.of(mary, jim));
        refrigerator.getUsers().addAll(List.of(mary, jim));

        mary.getHouses().add(refrigerator);
        jim.getHouses().add(refrigerator);

        invalidHouse = TestHouseFactory.invalid();
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
    void add_repositorySavesHouse() throws ValidationException {
        testService.add(cubby);

        Mockito.verify(mockHouseRepo).save(cubby);
    }

    @Test
    void add_repositorySavesAllUsers() throws ValidationException {
        testService.add(cubby);

        Mockito.verify(mockUserRepo).save(mary);
        Mockito.verify(mockUserRepo).save(jim);
    }

    @Test
    void add_usersReferenceHouse() throws ValidationException {
        testService.add(cubby);

        assertTrue(mary.getHouses().contains(cubby));
        assertTrue(jim.getHouses().contains(cubby));
    }

    @Test
    void add_fail_invalidHouse() {
        assertThrows(
                ValidationException.class,
                () -> testService.add(invalidHouse)
        );
    }

    @Test
    void delete_deletesHouse() {
        testService.delete(refrigerator);

        Mockito.verify(mockHouseRepo).delete(refrigerator);
    }

    @Test
    void delete_dereferencesUsers() {
        testService.delete(refrigerator);

        assertTrue(refrigerator.getUsers().isEmpty());
    }

    @Test
    void delete_usersForgetHouse() {
        Set<User> users = refrigerator.getUsers();
        int countOfUsers = users.size();
        testService.delete(refrigerator);

        users.forEach(user -> assertFalse(user.getHouses().contains(refrigerator)));
        Mockito.verify(mockUserRepo, times(countOfUsers)).save(any());
    }

    @Test
    void update_savesHouse() {
        // todo implement these
    }

    @Test
    void update_rejectsChangedUsers() {
        // todo implement
    }

    @Test
    void update_rejectsChangedProducts() {
        // todo implement
    }

    @Test
    void update_rejectsInvalidHouse() {
        // todo implement
    }
}