package ie.dempsey.kitchenstore.application.validators.house;

import ie.dempsey.kitchenstore.application.exceptions.ValidationException;
import ie.dempsey.kitchenstore.application.validators.Validator;
import ie.dempsey.kitchenstore.domain.entities.House;

public class UpdateHouseValidator implements Validator<House> {
    @Override
    public void validate(House house) throws ValidationException {
        /*
        id should be > 0
        created should be non null
        name should be non null or blank

         */
    }
}
