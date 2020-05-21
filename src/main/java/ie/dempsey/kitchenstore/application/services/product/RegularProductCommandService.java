package ie.dempsey.kitchenstore.application.services.product;

import ie.dempsey.kitchenstore.domain.entities.House;
import ie.dempsey.kitchenstore.domain.entities.Product;
import ie.dempsey.kitchenstore.domain.entities.Tag;

public class RegularProductCommandService implements ProductCommandService {
    @Override
    public void add(House house, Product product) {

    }

    @Override
    public void remove(House house, Product product) {

    }

    @Override
    public void setQuantity(Product product, int quantity) {

    }

    @Override
    public void moveTo(Product product, House source, House destination) {

    }

    @Override
    public void tag(Product product, Tag... tags) {

    }

    @Override
    public void makeGhost(Product ghost) {

    }
}
