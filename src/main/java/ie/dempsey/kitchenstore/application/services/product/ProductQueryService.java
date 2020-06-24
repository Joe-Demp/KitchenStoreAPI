package ie.dempsey.kitchenstore.application.services.product;

import ie.dempsey.kitchenstore.application.exceptions.NoSuchHouseException;
import ie.dempsey.kitchenstore.application.exceptions.NoSuchProductException;
import ie.dempsey.kitchenstore.domain.entities.Product;

import java.util.List;


public interface ProductQueryService {
    List<Product> getAll();

    List<Product> getFromHouse(long houseId) throws NoSuchHouseException;

    List<Product> getWithName(String name);

    List<Product> getWithName(String name, long houseId) throws NoSuchHouseException;

    Product getById(long id) throws NoSuchProductException;

    Product getByExample(Product product) throws NoSuchProductException;

    long countAll();
}
