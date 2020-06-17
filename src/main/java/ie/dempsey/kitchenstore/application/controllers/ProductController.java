package ie.dempsey.kitchenstore.application.controllers;

import ie.dempsey.kitchenstore.application.dtos.ProductDto;
import ie.dempsey.kitchenstore.application.exceptions.NoSuchHouseException;
import ie.dempsey.kitchenstore.application.exceptions.NoSuchProductException;
import ie.dempsey.kitchenstore.application.exceptions.ValidationException;
import ie.dempsey.kitchenstore.application.services.house.HouseQueryService;
import ie.dempsey.kitchenstore.application.services.product.ProductCommandService;
import ie.dempsey.kitchenstore.application.services.product.ProductQueryService;
import ie.dempsey.kitchenstore.application.validators.product.NewProductValidator;
import ie.dempsey.kitchenstore.domain.entities.House;
import ie.dempsey.kitchenstore.domain.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductQueryService queryService;
    private final ProductCommandService commandService;

    private final HouseQueryService houseQueryService;

    @Autowired
    public ProductController(
            ProductQueryService queryService,
            ProductCommandService commandService,
            HouseQueryService houseQueryService
    ) {
        this.queryService = queryService;
        this.commandService = commandService;
        this.houseQueryService = houseQueryService;
    }

    private static List<ProductDto> productsToDtos(List<Product> products) {
        return products.stream().map(ProductDto::new).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ProductDto byId(@PathVariable long id) throws NoSuchProductException {
        Product product = queryService.getById(id);
        return new ProductDto(product);
    }

    @GetMapping("/")
    public List<ProductDto> all() {
        return productsToDtos(queryService.getAll());
    }

    @GetMapping("/house")
    public List<ProductDto> fromHouse(@RequestParam long houseId) throws NoSuchHouseException {
        List<Product> productsFromHouse = queryService.getFromHouse(houseId);
        return productsToDtos(productsFromHouse);
    }

    @GetMapping(value = "/name", params = {"name"})
    public List<ProductDto> withName(@RequestParam String name) {
        List<Product> productsWithName = queryService.getWithName(name);
        return productsToDtos(productsWithName);
    }

    @GetMapping(value = "/name", params = {"name", "houseId"})
    public List<ProductDto> withName(
            @RequestParam String name, @RequestParam long houseId
    ) throws NoSuchHouseException {
        List<Product> productsWithName = queryService.getWithName(name, houseId);
        return productsToDtos(productsWithName);
    }

    @GetMapping("/count")
    public long count() {
        return queryService.countAll();
    }

    // This is a POST, because multiple requests will change the state on the server many times
    @PostMapping("/add")
    public ResponseEntity<ProductDto> add(
            @RequestParam long houseId, @RequestBody ProductDto productDto
    ) throws NoSuchHouseException, ValidationException {
        House house = houseQueryService.getById(houseId);
        Product newProduct = productDto.project();

        // todo move this out to the service layer with a "NewProductException"
        new NewProductValidator().validate(newProduct);

        commandService.add(house, newProduct);
        return new ResponseEntity<>(new ProductDto(newProduct), HttpStatus.CREATED);
    }
}
