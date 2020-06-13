package ie.dempsey.kitchenstore.application.services.product;

import ie.dempsey.kitchenstore.application.exceptions.NoSuchHouseException;
import ie.dempsey.kitchenstore.domain.entities.House;
import ie.dempsey.kitchenstore.domain.entities.Product;
import ie.dempsey.kitchenstore.infrastructure.repositories.HouseRepository;
import ie.dempsey.kitchenstore.infrastructure.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

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
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    /**
     * probably don't need this
     */
    @Override
    public List<Product> getFromHouse(House house) throws NoSuchHouseException {
        long houseId = house.getId();

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

    // todo deal with case sensitivity
    @Override
    public List<Product> getWithName(String name) {
        return getWithName(name, null);
    }

    @Override
    public List<Product> getWithName(String name, House house) {
        return null;
    }

    @Override
    public Product getById(long id) {
        return null;
    }

    @Override
    public long countAll() {
        return productRepository.count();
    }
}
