package ie.dempsey.kitchenstore.application.validators.house;

import ie.dempsey.kitchenstore.application.exceptions.ValidationException;
import ie.dempsey.kitchenstore.domain.entities.House;

import java.util.Objects;

public class UpdateHouseValidator extends AbstractHouseValidator {
    @Override
    public void validate(House house) throws ValidationException {
        validateCommonAttributes(house);

        if (house.getId() <= 0L) {
            throw new ValidationException("A house update must refer to a house with a valid id");
        } else if (Objects.isNull(house.getCreated())) {
            throw new ValidationException("A house's creation date cannot be erased on update");
        }
    }
}
