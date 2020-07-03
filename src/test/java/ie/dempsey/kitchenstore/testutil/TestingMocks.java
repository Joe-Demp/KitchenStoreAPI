package ie.dempsey.kitchenstore.testutil;

import ie.dempsey.kitchenstore.domain.entities.House;
import ie.dempsey.kitchenstore.infrastructure.repositories.HouseRepository;
import org.mockito.Mockito;
import org.springframework.data.domain.Example;

import java.util.List;
import java.util.Optional;

public class TestingMocks {
    public static List<House> allHouses;

    static {
        allHouses = TestHouseFactory.all();
    }

    public static HouseRepository mockHouseRepository() {
        HouseRepository repo = Mockito.mock(HouseRepository.class);
        Mockito.when(repo.findById(TestHouseFactory.FRIDGE_ID))
                .thenReturn(Optional.of(TestHouseFactory.fridge()));
        Mockito.when(repo.findAll()).thenReturn(allHouses);
        Mockito.when(repo.findAll(Mockito.any(Example.class)))
                .thenReturn(List.of(TestHouseFactory.fridge()));

        return repo;
    }
}
