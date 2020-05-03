package ie.dempsey.kitchenstore.domain.entities;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class HouseTests {
    private static final long sampleId = 87L;
    private static final Date sampleCreated =
            new Calendar.Builder().setDate(2020, 2, 14).build().getTime();
    private static final String sampleName = "Main Fridge";
    private static final String sampleDescription = "The main kitchen fridge.";
    private static final House.Type sampleType = House.Type.FRIDGE;
    private static final Product sampleProduct = new Product().setName("Milk");
    private static final User sampleUser = new User().setName("John Doe");

    private static List<Product> makeFridgeProducts() {
        List<Product> products = new ArrayList<>();
        products.add(sampleProduct);
        return products;
    }

    private static Set<User> makeFridgeUsers() {
        Set<User> users = new HashSet<>();
        users.add(sampleUser);
        return users;
    }

    private static House makeMyFridge() {
        return new House()
                .setId(sampleId)
                .setCreated(sampleCreated)
                .setName(sampleName)
                .setDescription(sampleDescription)
                .setType(sampleType)
                .setProducts(makeFridgeProducts())
                .setUsers(makeFridgeUsers());
    }

    @Test
    public void newHouseShouldHaveDefaultValues() {
        House house = new House();

        assertEquals(0L, house.getId());
        assertNull(house.getCreated());
        assertEquals("", house.getName());
        assertEquals("", house.getDescription());
        assertEquals(House.Type.CUPBOARD, house.getType());
        assertTrue(house.getProducts().isEmpty());
        assertTrue(house.getUsers().isEmpty());
    }

    @Test
    public void modifiedHouseShouldHaveSpecialValues() {
        House fridge = makeMyFridge();

        assertEquals(sampleId, fridge.getId());
        assertEquals(sampleCreated, fridge.getCreated());
        assertEquals(sampleName, fridge.getName());
        assertEquals(sampleDescription, fridge.getDescription());
        assertEquals(sampleType, fridge.getType());

        assertEquals(1, fridge.getProducts().size());
        assertTrue(fridge.getProducts().contains(sampleProduct));
        assertEquals(1, fridge.getUsers().size());
        assertTrue(fridge.getUsers().contains(sampleUser));
    }

    @Test
    public void settersShouldReturnTheHouse() {
        House fridge = makeMyFridge();
        String newDescription = "The outside fridge";

        assertEquals(fridge, fridge.setDescription(newDescription));
        assertEquals(newDescription, fridge.getDescription());
    }
}
