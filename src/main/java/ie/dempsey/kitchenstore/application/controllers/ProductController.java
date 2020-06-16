package ie.dempsey.kitchenstore.application.controllers;

import ie.dempsey.kitchenstore.application.dtos.ProductDto;
import ie.dempsey.kitchenstore.application.exceptions.NoSuchProductException;
import ie.dempsey.kitchenstore.application.services.product.ProductCommandService;
import ie.dempsey.kitchenstore.application.services.product.ProductQueryService;
import ie.dempsey.kitchenstore.domain.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
public class ProductController {
    ProductQueryService queryService;
    ProductCommandService commandService;

    public ProductController(
            @Autowired ProductQueryService queryService,
            @Autowired ProductCommandService commandService
    ) {
        this.queryService = queryService;
        this.commandService = commandService;
    }

    @GetMapping("/")
    public List<ProductDto> all() {
        return queryService.getAll().stream().map(ProductDto::new).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ProductDto byId(@PathVariable long id) throws NoSuchProductException {
        Product product = queryService.getById(id);
        return new ProductDto(product);
    }

    @GetMapping("/house")
    public List<ProductDto> fromHouse(@RequestParam long id) {
        return new ArrayList<>();
    }
}
