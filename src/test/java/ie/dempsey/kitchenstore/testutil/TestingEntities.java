package ie.dempsey.kitchenstore.testutil;

import ie.dempsey.kitchenstore.domain.entities.House;
import ie.dempsey.kitchenstore.domain.entities.Product;
import ie.dempsey.kitchenstore.domain.entities.tags.PerishableTag;
import ie.dempsey.kitchenstore.domain.entities.tags.Tag;
import ie.dempsey.kitchenstore.domain.entities.tags.UseFirstTag;

import java.util.Calendar;
import java.util.Date;

/**
 * A class to supply entities for tests.
 *
 * <p>This class should not be changed without due consideration of the tests that
 * depend on these entities.</p>
 */
public class TestingEntities {
    public static final Date MARCH_2019;
    public static final Date MAY_2018;

    public static final House CUPBOARD;
    public static final House REFRIGERATOR;

    public static final Product LEMONADE;
    public static final Product STEAK;
    public static final Product CEREAL;

    public static final Tag[] TAGS;

    static {
        Calendar cal = new Calendar.Builder().build();

        cal.set(2019, Calendar.MARCH, 12);
        MARCH_2019 = cal.getTime();

        cal.set(2018, Calendar.MAY, 23);
        MAY_2018 = cal.getTime();

        CUPBOARD = new House()
                .setName("Cupboard")
                .setDescription("The press in the kitchen")
                .setCreated(MARCH_2019)
                .setType(House.Type.CUPBOARD);

        REFRIGERATOR = new House()
                .setName("Fridge")
                .setCreated(MAY_2018)
                .setType(House.Type.FRIDGE);

        LEMONADE = new Product()
                .setName("Lemonade")
                .setQuantity(2)
                .setHouse(CUPBOARD);

        STEAK = new Product()
                .setName("Steak")
                .setQuantity(4);

        CEREAL = new Product()
                .setName("Cereal")
                .setQuantity(1)
                .setHouse(CUPBOARD);

        CUPBOARD.getProducts().add(LEMONADE);
        CUPBOARD.getProducts().add(CEREAL);

        TAGS = new Tag[]{
                new PerishableTag(),
                new UseFirstTag()
        };
    }
}
