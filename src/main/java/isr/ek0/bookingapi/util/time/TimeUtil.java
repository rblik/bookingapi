package isr.ek0.bookingapi.util.time;

import org.springframework.util.StringUtils;

import java.time.LocalDate;

public class TimeUtil {
    public static LocalDate parseLocalDate(String str) {
        return StringUtils.isEmpty(str) ? null : LocalDate.parse(str);
    }
}
