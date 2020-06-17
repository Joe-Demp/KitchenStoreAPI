package ie.dempsey.kitchenstore.application.services.house;

import ie.dempsey.kitchenstore.application.exceptions.NoSuchHouseException;
import ie.dempsey.kitchenstore.domain.entities.House;

public interface HouseQueryService {
    House getById(long id) throws NoSuchHouseException;
}
