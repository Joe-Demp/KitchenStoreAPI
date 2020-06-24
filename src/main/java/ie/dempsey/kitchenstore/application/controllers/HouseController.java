package ie.dempsey.kitchenstore.application.controllers;

import ie.dempsey.kitchenstore.application.dtos.HouseDto;
import ie.dempsey.kitchenstore.application.exceptions.NoSuchHouseException;
import ie.dempsey.kitchenstore.application.exceptions.NoSuchUserException;
import ie.dempsey.kitchenstore.application.services.house.HouseQueryService;
import ie.dempsey.kitchenstore.domain.entities.House;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/house")
public class HouseController {
    private final HouseQueryService queryService;

    @Autowired
    public HouseController(HouseQueryService queryService) {
        this.queryService = queryService;
    }

    // todo implement the methods below

    private static List<HouseDto> housesToDtos(List<House> houses) {
        return new ArrayList<>();
    }

    @GetMapping("/{id}")
    public House byId(@PathVariable long id) throws NoSuchHouseException {
        return queryService.getById(id);
    }

    @GetMapping("/user/{uid}")
    public List<House> byUserId(@PathVariable long uid) throws NoSuchHouseException, NoSuchUserException {
        // todo use a userQueryService to sort this out
        return new ArrayList<>();
    }

    @GetMapping("/")
    public List<House> all() {
        return new ArrayList<>();
    }

    @GetMapping("/name")
    public List<HouseDto> withName(@RequestParam String name) {
        return new ArrayList<>();
    }

    @PostMapping("/add")
    public ResponseEntity<HouseDto> add(@RequestBody HouseDto houseDto) {
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
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
