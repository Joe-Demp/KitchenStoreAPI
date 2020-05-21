package ie.dempsey.kitchenstore.application.services.product;

import ie.dempsey.kitchenstore.domain.entities.House;
import ie.dempsey.kitchenstore.domain.entities.Product;

import java.util.List;

public interface ProductQueryService {
    List<Product> getProducts(House house);

    long countAllProducts();
}
