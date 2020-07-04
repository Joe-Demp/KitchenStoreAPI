package ie.dempsey.kitchenstore.application.controllers;

import ie.dempsey.kitchenstore.application.dtos.HouseDto;
import ie.dempsey.kitchenstore.application.exceptions.NoSuchHouseException;
import ie.dempsey.kitchenstore.application.exceptions.NoSuchUserException;
import ie.dempsey.kitchenstore.application.services.house.HouseCommandService;
import ie.dempsey.kitchenstore.application.services.house.HouseQueryService;
import ie.dempsey.kitchenstore.application.services.user.UserQueryService;
import ie.dempsey.kitchenstore.domain.entities.House;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/house")
public class HouseController {
    private final HouseQueryService queryService;
    private final HouseCommandService commandService;
    private final UserQueryService userQueryService;

    @Autowired
    public HouseController(HouseQueryService queryService, HouseCommandService commandService, UserQueryService userQueryService) {
        this.queryService = queryService;
        this.commandService = commandService;
        this.userQueryService = userQueryService;
    }

    // todo implement the methods below

    private static List<HouseDto> housesToDtos(Collection<House> houses) {
        return houses.stream().map(HouseDto::new).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public House byId(@PathVariable long id) throws NoSuchHouseException {
        return queryService.getById(id);
    }

    @GetMapping("/user/{id}")
    public List<HouseDto> byUserId(@PathVariable long id) throws NoSuchUserException {
        Set<House> houses = userQueryService.getById(id).getHouses();
        return housesToDtos(houses);
    }

    @GetMapping("/")
    public List<House> all() {
        return queryService.getAll();
    }

    @GetMapping("/{name}")
    public List<HouseDto> withName(@PathVariable String name) {
        List<House> houses = queryService.getWithName(name);
        return housesToDtos(houses);
    }

    @PostMapping("/add")
    public ResponseEntity<HouseDto> add(@RequestBody HouseDto houseDto) {
        return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
    }

    @PostMapping("/upd")
    public ResponseEntity<HouseDto> update(@RequestBody HouseDto houseDto) {
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    /**
     * Removes a {@code House} and all of its products from the system.
     * There is no way of recovering the {@code Products} that were contained in the house after this method.
     * Consider saving them or using {@link #untrack} if you need to retain the {@code Products}.
     *
     * @return the {@code House} that was just deleted.
     */
    @PostMapping("/del")
    public ResponseEntity<HouseDto> delete(@RequestBody HouseDto houseDto) {
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    /**
     * This method removes the {@code House} specified, but preserves the orphaned {@code Products}.
     * The {@code Products} should be placed into a system defined house that the user cannot modify.
     *
     * @return The {@code House} that was just untracked.
     */
    @PostMapping("/untrack")
    public ResponseEntity<HouseDto> untrack(@RequestBody HouseDto houseDto) {
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}
