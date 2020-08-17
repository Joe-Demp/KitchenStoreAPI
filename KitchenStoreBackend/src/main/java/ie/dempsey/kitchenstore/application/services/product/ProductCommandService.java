package ie.dempsey.kitchenstore.application.services.product;

import ie.dempsey.kitchenstore.domain.entities.House;
import ie.dempsey.kitchenstore.domain.entities.Product;
import ie.dempsey.kitchenstore.domain.entities.tags.Tag;

public interface ProductCommandService {
    void add(Product product);

    void remove(Product product);

    /**
     * Changes a product that has already been persisted. This method will not move a product from one House to another.
     * To do so, use {@link #moveTo}.
     */
    void update(Product product);

    void moveTo(Product product, House destination);

    void tag(Product product, Tag... tags);

    void makeGhost(Product ghost);
}
