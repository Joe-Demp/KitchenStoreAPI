package ie.dempsey.kitchenstore.domain.entities;

import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ProductTests {
    public static final long sampleId = 23L;
    public static final Date sampleCreated =
            new Calendar.Builder().setDate(2020, 2, 14).build().getTime();
    public static final Date sampleExpiry =
            new Calendar.Builder().setDate(2020, 2, 28).build().getTime();
    public static final String sampleName = "Low-fat Milk";
    public static final String sampleDescription = "Tesco brand low fat milk";

    private static Product makeMilk() {
        return new Product()
                .setId(sampleId)
                .setCreated(sampleCreated)
                .setExpiry(sampleExpiry)
                .setName(sampleName)
                .setDescription(sampleDescription);
    }

    @Test
    public void newProductShouldHaveDefaultValues() {
        Product emptyProduct = new Product();

        assertEquals(0L, emptyProduct.getId());
        assertNull(emptyProduct.getCreated());
        assertNull(emptyProduct.getExpiry());
        assertEquals("", emptyProduct.getName());
        assertEquals("", emptyProduct.getDescription());
    }

    @Test
    public void modifiedProductShouldHaveSpecialValues() {
        Product milk = makeMilk();

        assertEquals(sampleId, milk.getId());
        assertEquals(sampleCreated, milk.getCreated());
        assertEquals(sampleExpiry, milk.getExpiry());
        assertEquals(sampleName, milk.getName());
        assertEquals(sampleDescription, milk.getDescription());
    }

    @Test
    public void settersShouldReturnTheProduct() {
        Product milk = makeMilk();
        String newDescription = "Sour milk. Must use in bread.";

        assertEquals(milk, milk.setDescription(newDescription));
        assertEquals(newDescription, milk.getDescription());
    }
}
