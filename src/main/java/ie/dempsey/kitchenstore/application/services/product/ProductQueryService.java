package ie.dempsey.kitchenstore.application.services.product;

import ie.dempsey.kitchenstore.domain.entities.House;
import ie.dempsey.kitchenstore.domain.entities.Product;

import java.util.List;


public interface ProductQueryService {
    List<Product> getAllProducts();

    List<Product> getProducts(House house);

    List<Product> getProductsWithName(String name);

    List<Product> getProductsWithName(String name, House house);

    Product getProductById(long id);

    long countProducts();
}
