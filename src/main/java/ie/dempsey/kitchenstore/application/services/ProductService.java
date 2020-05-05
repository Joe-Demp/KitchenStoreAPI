package ie.dempsey.kitchenstore.application.services;

import ie.dempsey.kitchenstore.domain.entities.House;
import ie.dempsey.kitchenstore.domain.entities.Product;
import ie.dempsey.kitchenstore.domain.entities.Tag;

import java.util.List;

public interface ProductService {
    List<Product> getProducts(House house);

    void add(House house, Product product);

    void remove(House house, Product product);

    void setQuantity(Product product, int quantity);

    void moveTo(Product product, House source, House destination);

    void tag(Product product, Tag... tags);

    void makeGhost(Product ghost);
}
