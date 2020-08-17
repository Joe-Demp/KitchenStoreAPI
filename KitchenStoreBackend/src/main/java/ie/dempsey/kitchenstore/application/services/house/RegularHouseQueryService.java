package ie.dempsey.kitchenstore.application.services.house;

import ie.dempsey.kitchenstore.application.exceptions.NoSuchHouseException;
import ie.dempsey.kitchenstore.domain.entities.House;
import ie.dempsey.kitchenstore.infrastructure.repositories.HouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegularHouseQueryService implements HouseQueryService {
    private final HouseRepository repository;

    public RegularHouseQueryService(@Autowired HouseRepository repository) {
        this.repository = repository;
    }

    @Override
    public House getById(long id) throws NoSuchHouseException {
        return repository.findById(id).orElseThrow(() -> {
            String message = String.format("The house with id=%d does not exist.", id);
            return new NoSuchHouseException(message);
        });
    }

    @Override
    public List<House> getAll() {
        return repository.findAll();
    }

    @Override
    public List<House> getWithName(String name) {
        House houseWithName = new House().setName(name);
        Example<House> exampleHouseWithName = Example.of(houseWithName, NameMatcher.getInstance());
        return repository.findAll(exampleHouseWithName);
    }

    /**
     * A class to generate a singleton Example matcher that matches entities by name, ignoring case.
     */
    private static class NameMatcher {
        private static ExampleMatcher instance;

        public static ExampleMatcher getInstance() {
            if (instance == null) {
                instance = ExampleMatcher
                        .matchingAll()
                        .withMatcher(
                                "name",
                                ExampleMatcher.GenericPropertyMatchers.ignoreCase()
                        )
                ;
            }
            return instance;
        }
    }
}
