package isr.ek0.bookingapi.web;

import isr.ek0.bookingapi.model.Booking;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import static isr.ek0.bookingapi.testutils.BookingUtil.*;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyMap;
import static java.util.Collections.singletonList;
import static org.junit.Assert.assertEquals;
import static org.springframework.http.HttpStatus.OK;

public class BookingControllerTest extends BaseControllerTest{

    @Test
    public void testGetAll() {
        ResponseEntity<Booking[]> bookingsEntity = restTemplate.getForEntity("/bookings", Booking[].class, emptyMap());
        LOGGER.debug(bookingsEntity.toString());
        assertEquals(OK, bookingsEntity.getStatusCode());
        assertEquals(singletonList(BOOKING_1), asList(bookingsEntity.getBody()));
    }

    @Test
    public void testSave() {
        ResponseEntity<Booking> bookingsEntity = restTemplate.postForEntity("/bookings/the_table", NEW_BOOKING, Booking.class, emptyMap());
        LOGGER.debug(bookingsEntity.toString());
        assertEquals(OK, bookingsEntity.getStatusCode());
        assertEquals(NEW_BOOKING_SAVED, bookingsEntity.getBody());
    }
}