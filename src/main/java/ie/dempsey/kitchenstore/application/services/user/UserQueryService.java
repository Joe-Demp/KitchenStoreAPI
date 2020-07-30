package ie.dempsey.kitchenstore.application.services.user;

import ie.dempsey.kitchenstore.application.exceptions.NoSuchUserException;
import ie.dempsey.kitchenstore.domain.entities.User;

import java.util.Set;

public interface UserQueryService {
    User getById(long id) throws NoSuchUserException;

    Set<User> all();
}
