package ie.dempsey.kitchenstore.application.services.house;

import ie.dempsey.kitchenstore.application.exceptions.NoSuchHouseException;
import ie.dempsey.kitchenstore.application.exceptions.ValidationException;
import ie.dempsey.kitchenstore.application.validators.house.NewHouseValidator;
import ie.dempsey.kitchenstore.application.validators.house.UpdateHouseValidator;
import ie.dempsey.kitchenstore.domain.entities.House;
import ie.dempsey.kitchenstore.domain.entities.User;
import ie.dempsey.kitchenstore.infrastructure.repositories.HouseRepository;
import ie.dempsey.kitchenstore.infrastructure.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class RegularHouseCommandService implements HouseCommandService {

    private final HouseRepository repository;
    private final UserRepository userRepository;

    final NewHouseValidator newHouseValidator = new NewHouseValidator();
    final UpdateHouseValidator updateHouseValidator = new UpdateHouseValidator();

    @Autowired
    public RegularHouseCommandService(HouseRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    @Override
    public void add(House house) throws ValidationException {
        newHouseValidator.validate(house);

        addHouseToUsers(house);
        repository.save(house);
        house.getUsers().forEach(userRepository::save);
    }

    @Override
    public void delete(House house) {
        repository.delete(house);
        removeHouseFromUsers(house);
        house.setUsers(new HashSet<>());
    }

    private void removeHouseFromUsers(House house) {
        for (User user : house.getUsers()) {
            user.getHouses().remove(house);
            userRepository.save(user);
        }
    }

    @Override
    public void update(House house) throws NoSuchHouseException, ValidationException {
        updateHouseValidator.validate(house);

        long updateId = house.getId();
        House toUpdate = repository.findById(updateId).orElseThrow(() -> {
            String msg = String.format("Cannot update non-existent house with id=%d", updateId);
            return new NoSuchHouseException(msg);
        });

        house.setUsers(toUpdate.getUsers());
        house.setProducts(toUpdate.getProducts());

        repository.save(house);
    }

    private void addHouseToUsers(House house) {
        for (User user : house.getUsers()) {
            user.getHouses().add(house);
        }
    }
}
