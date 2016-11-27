package isr.ek0.bookingapi.service;

import isr.ek0.bookingapi.model.Booking;
import org.junit.Test;
import java.util.List;

import static isr.ek0.bookingapi.testutils.BookingUtil.NEW_BOOKING;
import static isr.ek0.bookingapi.testutils.RestaurantUtil.ADMIN1_RESTAURANT1;
import static isr.ek0.bookingapi.testutils.UsersUtil.ADMIN_1;
import static isr.ek0.bookingapi.testutils.UsersUtil.USER_1;

public class BookingServiceTest extends BaseServiceTest{

    @Test
    public void testSave() {
        bookingService.save(USER_1.getEmail(), NEW_BOOKING);
    }

    @Test
    public void testGetAll() {
        List<Booking> all = bookingService.getAll(USER_1.getEmail());
        System.out.println(all);
    }

    @Test
    public void testGetAllByRestaurantName() {
        List<Booking> all = bookingService.getAllByRestaurantName(ADMIN_1.getEmail(), ADMIN1_RESTAURANT1.getName());
        System.out.println(all);
    }
}