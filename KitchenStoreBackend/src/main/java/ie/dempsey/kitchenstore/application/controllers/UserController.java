package ie.dempsey.kitchenstore.application.controllers;

import ie.dempsey.kitchenstore.domain.entities.User;
import ie.dempsey.kitchenstore.infrastructure.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable long id) {
        Optional<User> maybeUser = userRepository.findById(id);
        return maybeUser.orElse(null);
    }
}
