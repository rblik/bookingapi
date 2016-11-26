package isr.ek0.bookingapi.util;

import isr.ek0.bookingapi.model.Booking;
import isr.ek0.bookingapi.model.BookingId;

import java.time.LocalTime;
import java.util.List;

import static com.google.common.collect.ImmutableList.of;
import static isr.ek0.bookingapi.util.RestaurantUtil.ADMIN1_RESTAURANT1;
import static isr.ek0.bookingapi.util.UsersUtil.USER_1;
import static isr.ek0.bookingapi.util.UsersUtil.USER_2;
import static java.time.LocalDate.now;

public class BookingUtil {
    public static final Booking BOOKING_1 = new Booking(new BookingId(USER_1.getEmail(), now()), LocalTime.of(18, 0), ADMIN1_RESTAURANT1.getName());
    public static final Booking BOOKING_2 = new Booking(new BookingId(USER_2.getEmail(), now()), LocalTime.of(20, 0), ADMIN1_RESTAURANT1.getName());
    public static final List<Booking> BOOKINGS = of(BOOKING_1, BOOKING_2);
    public static final Booking NEW_BOOKING = new Booking(now().plusDays(1), LocalTime.of(21, 0), ADMIN1_RESTAURANT1.getName());
    public static final Booking NEW_BOOKING_SAME_DAY = new Booking(now().plusDays(0), LocalTime.of(21, 0), ADMIN1_RESTAURANT1.getName());
}
