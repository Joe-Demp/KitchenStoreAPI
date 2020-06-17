package ie.dempsey.kitchenstore.application.validators;

import ie.dempsey.kitchenstore.application.exceptions.ValidationException;

@FunctionalInterface
public interface Validator<T> {
    void validate(T t) throws ValidationException;
}
