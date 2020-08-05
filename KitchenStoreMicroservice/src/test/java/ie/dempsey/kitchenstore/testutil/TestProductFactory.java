package ie.dempsey.kitchenstore.testutil;

import ie.dempsey.kitchenstore.domain.entities.Product;

import java.util.List;

public class TestProductFactory {
    public static final long STEAK_ID = 20;

    public static List<Product> all() {
        return List.of(
                lemonade(),
                steak(),
                cereal(),
                carrot(),
                nutella()
        );
    }

    public static Product lemonade() {
        return new Product()
                .setName("lemonade")
                .setQuantity(8);
    }

    public static Product steak() {
        return new Product()
                .setId(STEAK_ID)
                .setName("Steak")
                .setQuantity(4);
    }

    public static Product cereal() {
        return new Product()
                .setName("Cereal")
                .setQuantity(1);
    }

    public static Product carrot() {
        return new Product()
                .setName("Carrots")
                .setQuantity(10);
    }

    public static Product nutella() {
        return new Product()
                .setName("Nutella")
                .setQuantity(1);
    }
}
