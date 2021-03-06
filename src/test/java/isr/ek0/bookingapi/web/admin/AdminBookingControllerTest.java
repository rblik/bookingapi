package isr.ek0.bookingapi.web.admin;

import isr.ek0.bookingapi.model.Booking;
import isr.ek0.bookingapi.web.BaseControllerTest;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;

import static isr.ek0.bookingapi.testutils.BookingData.BOOKINGS;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyMap;
import static org.junit.Assert.assertEquals;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.OK;

public class AdminBookingControllerTest extends BaseControllerTest {

    @Test
    public void testGetByRestaurantsName() {
        ResponseEntity<Booking[]> bookingsEntity = restTemplate.exchange("/admin/restaurants/the_table/bookings?date=2017-12-28", GET, new HttpEntity<String>(setBasicAuth("admin@gmail.com", "admin")), Booking[].class, emptyMap());
        LOGGER.debug(bookingsEntity.toString());
        assertEquals(OK, bookingsEntity.getStatusCode());
        assertEquals(BOOKINGS, asList(bookingsEntity.getBody()));
    }

    @Test
    public void testGetByRestaurantsNameForbidden() {
        ResponseEntity<Void> bookingsEntity = restTemplate.exchange("/admin/restaurants/the_table/bookings?date=2017-12-28", GET, new HttpEntity<String>(setBasicAuth("user@yandex.ru", "password")), Void.class, emptyMap());
        assertEquals(FORBIDDEN, bookingsEntity.getStatusCode());
    }
}
