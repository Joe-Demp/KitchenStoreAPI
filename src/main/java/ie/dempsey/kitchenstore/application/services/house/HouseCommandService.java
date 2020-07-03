package ie.dempsey.kitchenstore.application.services.house;

import ie.dempsey.kitchenstore.domain.entities.House;

public interface HouseCommandService {
    void add(House house);

    void remove(House house);

    /**
     * This method will not change the house's products, nor will it adjust the house's Users
     */
    void update(House house);
}
