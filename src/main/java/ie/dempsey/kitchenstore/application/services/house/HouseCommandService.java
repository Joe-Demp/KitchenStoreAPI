package ie.dempsey.kitchenstore.application.services.house;

import ie.dempsey.kitchenstore.application.exceptions.ValidationException;
import ie.dempsey.kitchenstore.domain.entities.House;

public interface HouseCommandService {
    /**
     * Adds a house to the application and associates it with a user
     */
    void add(House house) throws ValidationException;

    /**
     * Removes a {@code House} from persistence, disconnecting it from any owning Users.
     */
    void delete(House house);

    /**
     * This method will not change the house's products, nor will it adjust the house's Users
     */
    void update(House house);
}
