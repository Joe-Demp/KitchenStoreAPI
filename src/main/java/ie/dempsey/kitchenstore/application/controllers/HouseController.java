package ie.dempsey.kitchenstore.application.controllers;

import ie.dempsey.kitchenstore.domain.entities.House;
import ie.dempsey.kitchenstore.infrastructure.repositories.HouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/house")
public class HouseController {
    @Autowired
    private HouseRepository houseRepository;

    @GetMapping("/")
    public List<House> findAll() {
        return houseRepository.findAll();
    }

    @GetMapping("/{id}")
    public House findById(@PathVariable long id) {
        Optional<House> maybeHouse = houseRepository.findById(id);
        return maybeHouse.orElse(null);
    }
}
