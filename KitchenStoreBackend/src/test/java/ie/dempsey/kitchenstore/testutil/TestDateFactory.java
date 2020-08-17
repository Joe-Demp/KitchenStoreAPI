package ie.dempsey.kitchenstore.testutil;

import java.util.Calendar;
import java.util.Date;

public class TestDateFactory {
    public static final Date MARCH_2019;
    public static final Date MAY_2018;

    static {
        Calendar cal = new Calendar.Builder().build();

        cal.set(2019, Calendar.MARCH, 12);
        MARCH_2019 = cal.getTime();

        cal.set(2018, Calendar.MAY, 23);
        MAY_2018 = cal.getTime();
    }
}
