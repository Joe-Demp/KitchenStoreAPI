package ie.dempsey.kitchenstore.application.validators.house;

import ie.dempsey.kitchenstore.application.exceptions.ValidationException;
import ie.dempsey.kitchenstore.application.validators.Validator;
import ie.dempsey.kitchenstore.domain.entities.House;

import java.util.Objects;

public class NewHouseValidator implements Validator<House> {
    @Override
    public void validate(House house) throws ValidationException {
        if (house.getId() != 0L) {
            throw new ValidationException("A new house cannot have a user defined id");
        } else if (Objects.nonNull(house.getCreated())) {
            throw new ValidationException("New houses should not have a created date.");
        } else if (Objects.isNull(house.getName()) || house.getName().isBlank()) {
            throw new ValidationException("Every house should have a name");
        } else if (Objects.isNull(house.getUsers()) || house.getUsers().isEmpty()) {
            throw new ValidationException("Every house needs at least one user");
        }
    }
}
