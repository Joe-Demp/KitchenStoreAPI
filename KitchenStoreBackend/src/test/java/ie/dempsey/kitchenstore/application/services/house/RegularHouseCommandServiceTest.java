package ie.dempsey.kitchenstore.application.services.house;

import ie.dempsey.kitchenstore.application.exceptions.NoSuchHouseException;
import ie.dempsey.kitchenstore.application.exceptions.ValidationException;
import ie.dempsey.kitchenstore.domain.entities.House;
import ie.dempsey.kitchenstore.domain.entities.Product;
import ie.dempsey.kitchenstore.domain.entities.User;
import ie.dempsey.kitchenstore.infrastructure.repositories.HouseRepository;
import ie.dempsey.kitchenstore.infrastructure.repositories.UserRepository;
import ie.dempsey.kitchenstore.testutil.TestHouseFactory;
import ie.dempsey.kitchenstore.testutil.TestProductFactory;
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
    House nonExistentHouse;
    House invalidHouse;
    List<House> houseList;

    User mary, jim;
    List<User> userList;

    List<Product> productList;

    void initializeTestObjects() {
        mockHouseRepo = TestingMocks.mockHouseRepository();
        mockUserRepo = TestingMocks.mockUserRepository();
        testService = new RegularHouseCommandService(mockHouseRepo, mockUserRepo);

        productList = TestProductFactory.all();

        initializeUsers();
        initializeHouses();
        pointUsersToHouses();
    }

    void initializeUsers() {
        mary = TestUserFactory.mary();
        jim = TestUserFactory.jim();
        userList = List.of(mary, jim);
    }

    void initializeHouses() {
        cubby = TestHouseFactory.newCupboard();
        refrigerator = TestHouseFactory.fridge();
        invalidHouse = TestHouseFactory.invalid();
        nonExistentHouse = TestHouseFactory.nonExistent();

        List.of(cubby, refrigerator, nonExistentHouse).forEach(house -> house.getUsers().addAll(userList));
        refrigerator.getProducts().addAll(productList);
        houseList = List.of(cubby, refrigerator);
    }

    void pointUsersToHouses() {
        userList.forEach(user -> user.getHouses().addAll(houseList));
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
    void add_savesHouse() throws ValidationException {
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
    void update_savesHouse() throws NoSuchHouseException, ValidationException {
        refrigerator.setName("Chilly");
        testService.update(refrigerator);

        Mockito.verify(mockHouseRepo).save(refrigerator);
    }

    @Test
    void update_doesNotChangeUsers() throws NoSuchHouseException, ValidationException {
        int countOfUsers = refrigerator.getUsers().size();
        refrigerator.setUsers(TestUserFactory.alternatives());
        assertNotEquals(countOfUsers, refrigerator.getUsers().size());

        testService.update(refrigerator);
        assertEquals(countOfUsers, refrigerator.getUsers().size());
    }

    @Test
    void update_doesNotChangeProducts() throws NoSuchHouseException, ValidationException {
        int countOfProducts = refrigerator.getProducts().size();
        refrigerator.getProducts().clear();
        assertNotEquals(countOfProducts, refrigerator.getProducts().size());

        testService.update(refrigerator);
        assertEquals(countOfProducts, refrigerator.getProducts().size());
    }

    @Test
    void update_rejectsNonExistentHouse() {
        assertThrows(NoSuchHouseException.class, () -> testService.update(nonExistentHouse));
    }

    @Test
    void update_rejectsInvalidHouse() {
        assertThrows(ValidationException.class, () ->
                testService.update(invalidHouse)
        );
    }
}
