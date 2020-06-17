package ie.dempsey.kitchenstore.testutil;

import ie.dempsey.kitchenstore.domain.entities.House;
import ie.dempsey.kitchenstore.domain.entities.Product;
import ie.dempsey.kitchenstore.domain.entities.tags.PerishableTag;
import ie.dempsey.kitchenstore.domain.entities.tags.Tag;
import ie.dempsey.kitchenstore.domain.entities.tags.UseFirstTag;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * A class to supply entities for tests.
 *
 * <p>This class should not be changed without due consideration of the tests that
 * depend on these entities.</p>
 */
public class TestEntityFactory {

    // todo change this into a test entity factory
    //  a new instance for each test method ensures tests are mutually exclusive

    public static final Date MARCH_2019;
    public static final Date MAY_2018;

    public static final long CUPBOARD_ID = 34;
    public static final long REFRIGERATOR_ID = 1902;

    public static final long NON_EXISTENT_ID = 400;
    public static final long STEAK_ID = 20;

    public static final Tag[] TAGS;

    static {
        Calendar cal = new Calendar.Builder().build();

        cal.set(2019, Calendar.MARCH, 12);
        MARCH_2019 = cal.getTime();

        cal.set(2018, Calendar.MAY, 23);
        MAY_2018 = cal.getTime();

        TAGS = new Tag[]{
                new PerishableTag(),
                new UseFirstTag()
        };
    }

    private House refrigerator;
    private House cupboard;

    private Product fridgeLemonade;
    private Product steak;
    private Product cupboardLemonade;
    private Product cereal;

    public List<House> houses() {
        return Arrays.asList(refrigerator(), cupboard());
    }

    public House refrigerator() {
        if (refrigerator == null) {
            refrigerator = new House()
                    .setId(REFRIGERATOR_ID)
                    .setName("Fridge")
                    .setCreated(MAY_2018)
                    .setType(House.Type.FRIDGE);
        }
        return refrigerator;
    }

    public House cupboard() {
        if (cupboard == null) {
            cupboard = new House()
                    .setId(CUPBOARD_ID)
                    .setName("Cupboard")
                    .setDescription("The press in the kitchen")
                    .setCreated(MARCH_2019)
                    .setType(House.Type.CUPBOARD);
        }
        return cupboard;
    }

    public List<Product> products() {
        return Arrays.asList(
                fridgeLemonade(),
                steak(),
                cupboardLemonade(),
                cereal()
        );
    }

    public Product fridgeLemonade() {
        if (fridgeLemonade == null) {
            fridgeLemonade = new Product()
                    .setName("lemonade")
                    .setQuantity(8);
        }
        return fridgeLemonade;
    }

    public Product cupboardLemonade() {
        if (cupboardLemonade == null) {
            cupboardLemonade = new Product()
                    .setName("Lemonade")
                    .setQuantity(2);
        }
        return cupboardLemonade;
    }

    public Product steak() {
        if (steak == null) {
            steak = new Product()
                    .setId(STEAK_ID)
                    .setName("Steak")
                    .setQuantity(4);
        }
        return steak;
    }

    public Product cereal() {
        if (cereal == null) {
            cereal = new Product()
                    .setName("Cereal")
                    .setQuantity(1);
        }
        return cereal;
    }
}
