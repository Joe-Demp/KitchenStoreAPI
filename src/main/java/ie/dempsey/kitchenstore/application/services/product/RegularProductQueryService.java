package ie.dempsey.kitchenstore.application.services.product;

import ie.dempsey.kitchenstore.domain.entities.House;
import ie.dempsey.kitchenstore.domain.entities.Product;

import java.util.List;

public class RegularProductQueryService implements ProductQueryService {
    @Override
    public List<Product> getProducts(House house) {
        return null;
    }

    @Override
    public long countAllProducts() {
        return 0;
    }
}
