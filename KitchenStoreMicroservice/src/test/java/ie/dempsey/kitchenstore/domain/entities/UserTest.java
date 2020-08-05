package ie.dempsey.kitchenstore.domain.entities;

import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {
    private static final long sampleId = 46L;
    private static final String sampleName = "John Doe";
    private static final Date sampleJoined =
            new Calendar.Builder().setDate(2020, 5, 2).build().getTime();
    private static final String samplePassword = "password";
    private static final User.Status sampleStatus = User.Status.ADMIN;
    private static final House sampleHouse = new House();

    private static User makeJohn() {
        return new User()
                .setId(sampleId)
                .setName(sampleName)
                .setJoined(sampleJoined)
                .setPassword(samplePassword)
                .setStatus(sampleStatus)
                .setHouses(makeJohnsHouses());
    }

    private static Set<House> makeJohnsHouses() {
        Set<House> houses = new HashSet<>();
        houses.add(sampleHouse);
        return houses;
    }

    @Test
    public void newUserShouldHaveDefaultValues() {
        User user = new User();

        assertEquals(0L, user.getId());
        assertEquals("", user.getName());
        assertNull(user.getJoined());
        assertNull(user.getPassword());
        assertEquals(User.Status.REGULAR, user.getStatus());
        assertTrue(user.getHouses().isEmpty());
    }

    @Test
    public void modifiedUserShouldHaveSpecialValues() {
        User john = makeJohn();

        assertEquals(sampleId, john.getId());
        assertEquals(sampleName, john.getName());
        assertEquals(sampleJoined, john.getJoined());
        assertEquals(samplePassword, john.getPassword());
        assertEquals(sampleStatus, john.getStatus());

        assertEquals(1, john.getHouses().size());
        assertTrue(john.getHouses().contains(sampleHouse));
    }

    @Test
    public void settersShouldReturnTheProduct() {
        User john = makeJohn();
        String newPassword = "MeInGreece!";

        assertEquals(john, john.setPassword(newPassword));
        assertEquals(newPassword, john.getPassword());
    }
}
