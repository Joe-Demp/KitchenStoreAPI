package ie.dempsey.kitchenstore.application.services.house;

import ie.dempsey.kitchenstore.domain.entities.House;
import ie.dempsey.kitchenstore.domain.entities.User;
import ie.dempsey.kitchenstore.infrastructure.repositories.HouseRepository;
import ie.dempsey.kitchenstore.infrastructure.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegularHouseCommandService implements HouseCommandService {

    private final HouseRepository repository;
    private final UserRepository userRepository;

    @Autowired
    public RegularHouseCommandService(HouseRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    @Override
    public void add(House house) {
        addHouseToUsers(house);
        repository.save(house);
        house.getUsers().forEach(userRepository::save);
    }

    @Override
    public void remove(House house) {

    }

    @Override
    public void update(House house) {

    }

    private void addHouseToUsers(House house) {
        for (User user : house.getUsers()) {
            user.getHouses().add(house);
        }
    }
}
