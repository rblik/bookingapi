package isr.ek0.bookingapi.service;

import isr.ek0.bookingapi.model.Booking;
import isr.ek0.bookingapi.util.exception.NotFoundException;
import isr.ek0.bookingapi.util.exception.WrongBookingException;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

import static isr.ek0.bookingapi.testutils.BookingUtil.*;
import static isr.ek0.bookingapi.testutils.RestaurantUtil.ADMIN1_RESTAURANT1;
import static isr.ek0.bookingapi.testutils.UsersUtil.ADMIN_1;
import static isr.ek0.bookingapi.testutils.UsersUtil.ADMIN_2;
import static isr.ek0.bookingapi.testutils.UsersUtil.USER_1;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;

public class BookingServiceTest extends BaseServiceTest {

    @Test
    public void testSave() {
        bookingService.save(USER_1.getEmail(), NEW_BOOKING);
        assertThat(bookingService.getAll(USER_1.getEmail()), is(BOOKINGS_USER1_WITH_NEW));
    }

    @Test(expected = NotFoundException.class)
    public void testSaveWithWrongRestaurant() {
        bookingService.save(USER_1.getEmail(), WRONG_RESTAURANT_NEW_BOOKING);
    }

    @Test
    public void testSaveSameDaySameUser() {
        bookingService.save(USER_1.getEmail(), NEW_BOOKING_SAME_DAY);
        assertThat(bookingService.getAll(USER_1.getEmail()), is(singletonList(NEW_BOOKING_SAME_DAY)));
    }

    @Test(expected = WrongBookingException.class)
    public void testWrongTimeSave() {
        bookingService.save(USER_1.getEmail(), LATE_BOOKING);
    }

    @Test(expected = WrongBookingException.class)
    public void testWrongDateTimeSave() {
        bookingService.save(USER_1.getEmail(), WRONG_BOOKING);
    }

    @Test(expected = WrongBookingException.class)
    public void testSaveInvalidDate() {
        bookingService.save(USER_1.getEmail(), NULL_DATE_BOOKING);
    }

    @Test
    public void testGetAll() {
        List<Booking> all = bookingService.getAll(USER_1.getEmail());
        assertEquals(singletonList(BOOKING_1), all);
    }

    @Test
    public void testGetAllByRestaurantName() {
        List<Booking> all = bookingService.getAllByRestaurantName(ADMIN_1.getEmail(), ADMIN1_RESTAURANT1.getName(), LocalDate.of(2016, 12, 8));
        assertEquals(BOOKINGS, all);
    }

    @Test(expected = NotFoundException.class)
    public void testGetAllByWrongRestaurantName() {
        List<Booking> all = bookingService.getAllByRestaurantName(ADMIN_2.getEmail(), ADMIN1_RESTAURANT1.getName(), LocalDate.of(2016, 12, 8));
        assertEquals(BOOKINGS, all);
    }

    @Test
    public void testDelete() {
        bookingService.delete(USER_1.getEmail(), LocalDate.of(2016, 12, 8));
        assertEquals(emptyList(), bookingService.getAll(USER_1.getEmail()));
    }

    @Test
    public void testDeleteAll() {
        bookingService.deleteAll(USER_1.getEmail());
    }
}