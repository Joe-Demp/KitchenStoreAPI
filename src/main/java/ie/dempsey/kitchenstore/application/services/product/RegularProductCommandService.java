package ie.dempsey.kitchenstore.application.services.product;

import ie.dempsey.kitchenstore.domain.entities.House;
import ie.dempsey.kitchenstore.domain.entities.Product;
import ie.dempsey.kitchenstore.domain.entities.Tag;
import ie.dempsey.kitchenstore.infrastructure.repositories.HouseRepository;
import ie.dempsey.kitchenstore.infrastructure.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/* Services contain business logic */

/**
 * All methods in this class assume that their arguments exist in the application already, unless the purpose of the
 * method is to add said argument to the KitchenStore.
 */
@Service
public class RegularProductCommandService implements ProductCommandService {
    private final HouseRepository houseRepository;
    private final ProductRepository productRepository;

    public RegularProductCommandService(
            @Autowired ProductRepository productRepository,
            @Autowired HouseRepository houseRepository
    ) {
        this.productRepository = productRepository;
        this.houseRepository = houseRepository;
    }

    public static <T> List<T> arrayToList(T[] arr) {
        return Arrays.stream(arr).collect(Collectors.toList());
    }

    /**
     * Adds a product to a given house in the KitchenStore.
     */
    @Override
    public void add(House house, Product product) {
        productRepository.save(product);
        putProductInHouse(house, product);
    }

    /**
     * Deletes the specified product from the KitchenStore, and removes it from the specified house.
     */
    @Override
    public void remove(House house, Product product) {
        productRepository.delete(product);
        takeProductOutOfHouse(house, product);
    }

    @Override
    public void setQuantity(Product product, int quantity) {
        product.setQuantity(quantity);
        productRepository.save(product);
    }

    @Override
    public void moveTo(Product product, House source, House destination) {
        takeProductOutOfHouse(source, product);
        putProductInHouse(destination, product);
    }

    @Override
    public void tag(Product product, Tag... tags) {
        Set<Tag> productTags = product.getTags();
        productTags.addAll(arrayToList(tags));
        productRepository.save(product);
    }

    /**
     * Puts a product in ghost mode. The product still exists in the KitchenStore and its {@code House} but its
     * quantity is zero. Useful for flagging items that are consumed regularly, to add to shopping lists.
     *
     * @param ghost The product you would like to flag as a ghost.
     */
    @Override
    public void makeGhost(Product ghost) {
        // todo define what the ghost tag means
        // todo add the ghost tag

        // todo decide whether the product should have its quantity reduced to zero to be a ghost
        // consider the situation where a homeowner always wants two cartons of milk in the house at once
        // should they have one carton of milk + one ghost carton?
        // todo consider how the User will manage the shopping list. Should the User specify a desired quantity
        //  for certain products? What about the same product spread accross different Houses?
    }

    private void putProductInHouse(House house, Product product) {
        house.getProducts().add(product);
        houseRepository.save(house);
    }

    private void takeProductOutOfHouse(House house, Product product) {
        List<Product> houseProducts = house.getProducts();
        houseProducts.remove(product);
        houseRepository.save(house);
    }
}
