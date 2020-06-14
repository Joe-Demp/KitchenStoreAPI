package ie.dempsey.kitchenstore.application.services.product;

import ie.dempsey.kitchenstore.application.exceptions.NoSuchHouseException;
import ie.dempsey.kitchenstore.application.exceptions.NoSuchProductException;
import ie.dempsey.kitchenstore.domain.entities.House;
import ie.dempsey.kitchenstore.domain.entities.Product;

import java.util.List;


public interface ProductQueryService {
    List<Product> getAll();

    List<Product> getFromHouse(House house) throws NoSuchHouseException;

    List<Product> getWithName(String name);

    List<Product> getWithName(String name, House house) throws NoSuchHouseException;

    Product getById(long id) throws NoSuchProductException;

    long countAll();
}
