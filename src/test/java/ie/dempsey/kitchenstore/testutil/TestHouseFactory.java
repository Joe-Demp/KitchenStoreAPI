package ie.dempsey.kitchenstore.testutil;

import ie.dempsey.kitchenstore.domain.entities.House;

import java.util.Arrays;
import java.util.List;

public class TestHouseFactory {
    public static final long FRIDGE_ID = 82;
    public static final long NON_EXISTENT_HOUSE_ID = 42;
    private static final long FREEZER_ID = 505;
    private static final long CUPBOARD_ID = 32;

    public static House fridge() {
        return new House()
                .setId(FRIDGE_ID)
                .setName("Fridge")
                .setCreated(TestDateFactory.MARCH_2019)
                ;
    }

    public static House freezer() {
        return new House()
                .setId(FREEZER_ID)
                ;
    }

    public static House newCupboard() {
        return new House()
                .setName("Cubby")
                .setDescription("My favourite cupboard.")
                ;
    }

    public static List<House> all() {
        return Arrays.asList(fridge(), freezer(), newCupboard());
    }

    /**
     * @return a {@code House} with a name containing only whitespace characters
     */
    public static House invalid() {
        return new House()
                .setName("    \n")
                ;
    }

    public static House nonExistent() {
        return new House()
                .setId(NON_EXISTENT_HOUSE_ID)
                .setName("My House")
                ;
    }
}
