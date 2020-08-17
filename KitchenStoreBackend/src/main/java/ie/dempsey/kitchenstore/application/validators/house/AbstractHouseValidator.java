package ie.dempsey.kitchenstore.application.validators.house;

import ie.dempsey.kitchenstore.application.exceptions.ValidationException;
import ie.dempsey.kitchenstore.application.validators.Validator;
import ie.dempsey.kitchenstore.domain.entities.House;

import java.util.Objects;

public abstract class AbstractHouseValidator implements Validator<House> {
    protected void validateCommonAttributes(House house) throws ValidationException {
        if (Objects.isNull(house.getName()) || house.getName().isBlank()) {
            throw new ValidationException("Every house should have a name");
        } else if (Objects.isNull(house.getUsers()) || house.getUsers().isEmpty()) {
            throw new ValidationException("Every house needs at least one user");
        }
    }
}
