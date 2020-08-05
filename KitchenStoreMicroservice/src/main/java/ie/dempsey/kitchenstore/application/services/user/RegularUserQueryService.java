package ie.dempsey.kitchenstore.application.services.user;

import ie.dempsey.kitchenstore.application.exceptions.NoSuchUserException;
import ie.dempsey.kitchenstore.domain.entities.User;
import ie.dempsey.kitchenstore.infrastructure.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class RegularUserQueryService implements UserQueryService {
    private final UserRepository repository;

    public RegularUserQueryService(@Autowired UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User getById(long id) throws NoSuchUserException {
        return repository.findById(id).orElseThrow(
                () -> new NoSuchUserException(String.format("No user with id=%d", id))
        );
    }

    @Override
    public Set<User> all() {
        return new HashSet<>(repository.findAll());
    }
}
