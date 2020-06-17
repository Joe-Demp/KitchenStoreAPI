package ie.dempsey.kitchenstore.application.services.house;

import ie.dempsey.kitchenstore.application.exceptions.NoSuchHouseException;
import ie.dempsey.kitchenstore.domain.entities.House;
import ie.dempsey.kitchenstore.infrastructure.repositories.HouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
