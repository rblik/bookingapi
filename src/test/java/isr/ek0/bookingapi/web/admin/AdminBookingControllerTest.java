package isr.ek0.bookingapi.web.admin;

import isr.ek0.bookingapi.model.Booking;
import isr.ek0.bookingapi.web.BaseControllerTest;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import static isr.ek0.bookingapi.testutils.BookingUtil.BOOKINGS;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyMap;
import static org.junit.Assert.assertEquals;
import static org.springframework.http.HttpStatus.OK;

public class AdminBookingControllerTest extends BaseControllerTest {

    @Test
    public void testGetByRestaurantsName() {
        ResponseEntity<Booking[]> bookingsEntity = restTemplate.getForEntity("/admin/restaurants/the_table/bookings?date=2016-12-08", Booking[].class, emptyMap());
        LOGGER.debug(bookingsEntity.toString());
        assertEquals(OK, bookingsEntity.getStatusCode());
        assertEquals(BOOKINGS, asList(bookingsEntity.getBody()));
    }
}
