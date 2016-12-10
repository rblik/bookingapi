package isr.ek0.bookingapi.testutils;

import isr.ek0.bookingapi.model.Booking;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static com.google.common.collect.ImmutableList.of;
import static isr.ek0.bookingapi.testutils.UserData.USER_1;
import static isr.ek0.bookingapi.testutils.UserData.USER_2;

public class BookingData {
    public static final Booking BOOKING_1 = new Booking(USER_1.getEmail(), LocalDate.of(2016, 12, 28), LocalTime.of(18, 0), RestaurantData.ADMIN1_RESTAURANT1.getName());
    public static final Booking BOOKING_2 = new Booking(USER_2.getEmail(), LocalDate.of(2016, 12, 28), LocalTime.of(20, 0), RestaurantData.ADMIN1_RESTAURANT1.getName());
    public static final List<Booking> BOOKINGS = of(BOOKING_1, BOOKING_2);
    public static final Booking NEW_BOOKING = new Booking(LocalDate.of(2016, 12, 28).plusDays(1), LocalTime.of(21, 0), RestaurantData.ADMIN1_RESTAURANT1.getName());
    public static final Booking NEW_BOOKING_SAVED = new Booking(USER_1.getEmail(), LocalDate.of(2016, 12, 28).plusDays(1), LocalTime.of(21, 0), RestaurantData.ADMIN1_RESTAURANT1.getName());
    public static final Booking WRONG_RESTAURANT_NEW_BOOKING = new Booking(LocalDate.of(2016, 12, 8).plusDays(1), LocalTime.of(21, 0), RestaurantData.ADMIN1_RESTAURANT1.getName() + "wrong");
    public static final Booking NEW_BOOKING_SAME_DAY = new Booking(LocalDate.of(2016, 12, 28), LocalTime.of(21, 0), RestaurantData.ADMIN1_RESTAURANT1.getName());
    public static final Booking LATE_BOOKING = new Booking(LocalDate.of(2016, 12, 28), LocalTime.of(22, 30), RestaurantData.ADMIN1_RESTAURANT2.getName());
    public static final Booking WRONG_BOOKING = new Booking(LocalDate.now(), LocalTime.now().plusHours(1), RestaurantData.ADMIN1_RESTAURANT1.getName());
    public static final Booking NULL_DATE_BOOKING = new Booking(null, LocalTime.of(16, 0), RestaurantData.ADMIN1_RESTAURANT1.getName());
    public static final List<Booking> BOOKINGS_USER1_WITH_NEW = of(BOOKING_1, NEW_BOOKING);

    public static final String bookingJson = "{\"date\":\"2016-12-29\",\"time\":\"15:00\"}";
    public static final String bookingJsonNotValidToLate = "{\"date\":\"2016-12-29\",\"time\":\"23:30\"}";
    public static final String bookingJsonNotValid = "{\"time\":\"15:00\"}";
}
