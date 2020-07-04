package ie.dempsey.kitchenstore.testutil;

import ie.dempsey.kitchenstore.domain.entities.User;

import java.util.Calendar;
import java.util.Date;

public class TestUserFactory {
    public static final long MARYS_ID = 23L;
    public static final Date MARY_JOINED = new Calendar.Builder()
            .setDate(2020, 15, 3).build().getTime();
    private static final long JIMS_ID = 44L;

    public static User mary() {
        return new User()
                .setId(MARYS_ID)
                .setName("Mary Joyce")
                .setStatus(User.Status.ADMIN)
                .setPassword("password")
                .setJoined(MARY_JOINED)
                ;
    }

    public static User jim() {
        return new User()
                .setId(JIMS_ID)
                .setName("Jim")
                ;
    }
}
