package ie.dempsey.kitchenstore.application.validators.product;

import ie.dempsey.kitchenstore.application.exceptions.ValidationException;
import ie.dempsey.kitchenstore.application.validators.Validator;
import ie.dempsey.kitchenstore.domain.entities.Product;
import org.hibernate.cfg.NotYetImplementedException;

// todo implement
public class UpdateProductValidator implements Validator<Product> {
    @Override
    public void validate(Product product) throws ValidationException {
        throw new NotYetImplementedException(
                String.format("%s not implemented", getClass().toString())
        );
    }
}
