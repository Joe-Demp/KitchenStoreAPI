package ie.dempsey.kitchenstore.application.controllers;

import ie.dempsey.kitchenstore.domain.entities.Product;
import ie.dempsey.kitchenstore.infrastructure.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/")
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @GetMapping("/{id}")
    public Product findById(@PathVariable long id) {
        Optional<Product> maybeProduct = productRepository.findById(id);
        return maybeProduct.orElse(null);
    }
}
