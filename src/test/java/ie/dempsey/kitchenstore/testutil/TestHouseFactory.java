package ie.dempsey.kitchenstore.testutil;

import ie.dempsey.kitchenstore.domain.entities.House;

import java.util.Arrays;
import java.util.List;

public class TestHouseFactory {
    public static final long FRIDGE_ID = 82;
    public static final long NON_EXISTENT_HOUSE_ID = 0;
    public static final int NUMBER_OF_HOUSES = 3;
    private static final long FREEZER_ID = 505;
    private static final long CUPBOARD_ID = 32;

    public static House fridge() {
        return new House()
                .setId(FRIDGE_ID)
                ;
    }

    public static House freezer() {
        return new House()
                .setId(FREEZER_ID)
                ;
    }

    public static House cupboard() {
        return new House()
                .setId(CUPBOARD_ID)
                ;
    }

    public static List<House> all() {
        return Arrays.asList(fridge(), freezer(), cupboard());
    }
}
