package ie.dempsey.kitchenstore.application.services.user;

import ie.dempsey.kitchenstore.application.exceptions.NoSuchUserException;
import ie.dempsey.kitchenstore.domain.entities.User;

public interface UserQueryService {
    User getById(long id) throws NoSuchUserException;
}
