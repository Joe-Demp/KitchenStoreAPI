package ie.dempsey.kitchenstore.application.validators.house;

import ie.dempsey.kitchenstore.application.exceptions.ValidationException;
import ie.dempsey.kitchenstore.domain.entities.House;

import java.util.Objects;

public class NewHouseValidator extends AbstractHouseValidator {
    @Override
    public void validate(House house) throws ValidationException {
        validateCommonAttributes(house);

        if (house.getId() != 0L) {
            throw new ValidationException("A new house cannot have a user defined id");
        } else if (Objects.nonNull(house.getCreated())) {
            throw new ValidationException("New houses should not have a created date.");
        }
    }
}
