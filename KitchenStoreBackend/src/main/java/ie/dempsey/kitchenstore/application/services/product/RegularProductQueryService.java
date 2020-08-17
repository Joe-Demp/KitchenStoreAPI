package ie.dempsey.kitchenstore.application.services.product;

import ie.dempsey.kitchenstore.application.exceptions.NoSuchHouseException;
import ie.dempsey.kitchenstore.application.exceptions.NoSuchProductException;
import ie.dempsey.kitchenstore.domain.entities.House;
import ie.dempsey.kitchenstore.domain.entities.Product;
import ie.dempsey.kitchenstore.infrastructure.repositories.HouseRepository;
import ie.dempsey.kitchenstore.infrastructure.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RegularProductQueryService implements ProductQueryService {
    private final ProductRepository productRepository;
    private final HouseRepository houseRepository;

    @Autowired
    public RegularProductQueryService(
            ProductRepository productRepository,
            HouseRepository houseRepository
    ) {
        this.productRepository = productRepository;
        this.houseRepository = houseRepository;
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    /**
     * probably don't need this
     */
    @Override
    public List<Product> getFromHouse(long houseId) throws NoSuchHouseException {
        if (houseId > 0) {
            Optional<House> optHouse = houseRepository.findById(houseId);
            if (optHouse.isPresent()) {
                return optHouse.get().getProducts();
            } else {
                String message = String.format("House with id=%d does not exist.", houseId);
                throw new NoSuchHouseException(message);
            }
        }

        throw new NoSuchHouseException(String.format("House cannot have invalid id %d", houseId));
    }

    @Override
    public List<Product> getWithName(String name) {
        List<Product> productsWithName = new ArrayList<>();
        try {
            productsWithName = getWithName(name, 0);
        } catch (NoSuchHouseException ignored) {
        }
        return productsWithName;
    }

    @Override
    public List<Product> getWithName(String name, long houseId) throws NoSuchHouseException {
        List<Product> allProducts;
        if (houseId <= 0) {
            allProducts = productRepository.findAll();
        } else {
            allProducts = getFromHouse(houseId);
        }

        return allProducts.stream().filter(p -> p.getName().equalsIgnoreCase(name)).collect(Collectors.toList());
    }

    @Override
    public Product getById(long id) throws NoSuchProductException {
        Optional<Product> optionalProduct = productRepository.findById(id);
        return optionalProduct.orElseThrow(() -> {
            String error = String.format("Product with id=%d does not exist", id);
            return new NoSuchProductException(error);
        });
    }

    @Override
    public Product getByExample(Product product) throws NoSuchProductException {
        Example<Product> productExample = Example.of(product);
        Optional<Product> optionalProduct = productRepository.findOne(productExample);
        return optionalProduct.orElseThrow(() -> {
            String error = String.format("Example product %s does not exist", product.getName());
            return new NoSuchProductException(error);
        });
    }

    @Override
    public long countAll() {
        return productRepository.count();
    }
}
