package ie.dempsey.kitchenstore.application.services.product;

import ie.dempsey.kitchenstore.domain.entities.House;
import ie.dempsey.kitchenstore.domain.entities.Product;
import ie.dempsey.kitchenstore.infrastructure.repositories.HouseRepository;
import ie.dempsey.kitchenstore.infrastructure.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class RegularProductQueryService implements ProductQueryService {
    private ProductRepository productRepository;
    private HouseRepository houseRepository;

    public RegularProductQueryService(
            @Autowired ProductRepository productRepository,
            @Autowired HouseRepository houseRepository
    ) {
        this.productRepository = productRepository;
        this.houseRepository = houseRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return null;
    }

    @Override
    public List<Product> getProducts(House house) {
        return null;
    }

    @Override
    public List<Product> getProductsWithName(String name) {
        return getProductsWithName(name, null);
    }

    @Override
    public List<Product> getProductsWithName(String name, House house) {
        return null;
    }

    @Override
    public Product getProductById(long id) {
        return null;
    }

    @Override
    public long countProducts() {
        return 0;
    }
}
