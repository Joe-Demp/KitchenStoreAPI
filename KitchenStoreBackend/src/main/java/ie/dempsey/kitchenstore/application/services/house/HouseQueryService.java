package ie.dempsey.kitchenstore.application.services.house;

import ie.dempsey.kitchenstore.application.exceptions.NoSuchHouseException;
import ie.dempsey.kitchenstore.domain.entities.House;

import java.util.List;

public interface HouseQueryService {
    House getById(long id) throws NoSuchHouseException;

    List<House> getAll();

    List<House> getWithName(String name);
}
