package ie.dempsey.kitchenstore.application.validators.product;

import ie.dempsey.kitchenstore.application.exceptions.ValidationException;
import ie.dempsey.kitchenstore.application.validators.Validator;
import ie.dempsey.kitchenstore.domain.entities.Product;


public class NewProductValidator implements Validator<Product> {
    @Override
    public void validate(Product product) throws ValidationException {
        if (product.getId() != 0) {
            throw new ValidationException("New products should not have ids.");
        } else if (product.getCreated() != null) {
            throw new ValidationException("New products should not have a created date.");
        } else if (product.getName() == null) {
            throw new ValidationException("All products should have a name");
        }
    }
}
