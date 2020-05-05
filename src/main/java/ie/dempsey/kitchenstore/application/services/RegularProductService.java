package ie.dempsey.kitchenstore.application.services;

import ie.dempsey.kitchenstore.domain.entities.House;
import ie.dempsey.kitchenstore.domain.entities.Product;
import ie.dempsey.kitchenstore.domain.entities.Tag;
import ie.dempsey.kitchenstore.infrastructure.repositories.HouseRepository;
import ie.dempsey.kitchenstore.infrastructure.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegularProductService implements ProductService {
    private ProductRepository productRepository;
    private HouseRepository houseRepository;

    @Autowired
    public RegularProductService(ProductRepository pr, HouseRepository hr) {
        productRepository = pr;
        houseRepository = hr;
    }

    @Override
    public List<Product> getProducts(House house) {
        throw new RuntimeException("Method not implemented");
    }

    @Override
    public void add(House house, Product product) {
        throw new RuntimeException("Method not implemented");
    }

    @Override
    public void remove(House house, Product product) {
        throw new RuntimeException("Method not implemented");
    }

    @Override
    public void setQuantity(Product product, int quantity) {
        throw new RuntimeException("Method not implemented");
    }

    @Override
    public void moveTo(Product product, House source, House destination) {
        throw new RuntimeException("Method not implemented");
    }

    @Override
    public void tag(Product product, Tag... tags) {
        throw new RuntimeException("Method not implemented");
    }

    @Override
    public void makeGhost(Product ghost) {
        throw new RuntimeException("Method not implemented");
    }
}
