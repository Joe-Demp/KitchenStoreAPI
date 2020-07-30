package ie.dempsey.kitchenstore.testutil;

import ie.dempsey.kitchenstore.domain.entities.User;

import java.util.Calendar;
import java.util.Date;
import java.util.Set;

public class TestUserFactory {
    public static final long MARYS_ID = 23L;
    private static final long JIMS_ID = 44L;
    private static final long PATSYS_ID = 105L;

    public static final Date MARY_JOINED = new Calendar.Builder()
            .setDate(2020, 15, 3).build().getTime();

    public static Set<User> all() {
        return Set.of(
                mary(),
                jim()
        );
    }

    public static Set<User> alternatives() {
        return Set.of(patsy());
    }

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

    public static User patsy() {
        return new User()
                .setId(PATSYS_ID)
                .setName("Patsy")
                ;
    }
}
