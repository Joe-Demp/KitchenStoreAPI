package ie.dempsey.kitchenstore.testutil;

import ie.dempsey.kitchenstore.domain.entities.House;
import ie.dempsey.kitchenstore.infrastructure.repositories.HouseRepository;
import ie.dempsey.kitchenstore.infrastructure.repositories.UserRepository;
import org.mockito.Mockito;
import org.springframework.data.domain.Example;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestingMocks {
    public static List<House> allHouses;

    static {
        allHouses = TestHouseFactory.all();
    }

    private static Optional<House> repositoryFridge() {
        House fridge = TestHouseFactory.fridge();
        fridge.setUsers(TestUserFactory.all()).setProducts(TestProductFactory.all());
        return Optional.of(fridge);
    }

    public static HouseRepository mockHouseRepository() {
        HouseRepository repo = mock(HouseRepository.class);
        Example<House> anyHouseExample = Mockito.any();

        when(repo.findById(TestHouseFactory.FRIDGE_ID)).thenReturn(repositoryFridge());
        when(repo.findById(TestHouseFactory.NON_EXISTENT_HOUSE_ID)).thenReturn(Optional.empty());
        when(repo.findAll()).thenReturn(allHouses);
        when(repo.findAll(anyHouseExample)).thenReturn(List.of(TestHouseFactory.fridge()));

        return repo;
    }

    public static UserRepository mockUserRepository() {
        return mock(UserRepository.class);
    }
}
