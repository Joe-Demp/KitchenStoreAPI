package ie.dempsey.kitchenstore.application.controllers;

import ie.dempsey.kitchenstore.application.dtos.ProductDto;
import ie.dempsey.kitchenstore.application.exceptions.NoSuchProductException;
import ie.dempsey.kitchenstore.application.services.product.ProductCommandService;
import ie.dempsey.kitchenstore.application.services.product.ProductQueryService;
import ie.dempsey.kitchenstore.domain.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

//todo give each command a Dto from which to extract fields from
//  the controller handles the commands and delegates to the correct service method

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
    public List<ProductDto> findAll() {
        return queryService.getAll().stream().map(ProductDto::new).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ProductDto findById(@PathVariable long id) {
        try {
            Product product = queryService.getById(id);
            return new ProductDto(product);
        } catch (NoSuchProductException e) {
            return ProductDto.EMPTY;
        }
    }
}
