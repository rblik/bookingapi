package isr.ek0.bookingapi.web.user;

import isr.ek0.bookingapi.model.Booking;
import isr.ek0.bookingapi.util.exception.ErrorInfo;
import isr.ek0.bookingapi.web.BaseControllerTest;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;

import static isr.ek0.bookingapi.testutils.BookingData.*;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyMap;
import static java.util.Collections.singletonList;
import static org.junit.Assert.assertEquals;
import static org.springframework.http.HttpMethod.*;
import static org.springframework.http.HttpStatus.*;

public class BookingControllerTest extends BaseControllerTest {

    @Test
    public void testGetAll() {
        ResponseEntity<Booking[]> bookingsEntity = restTemplate.exchange("/bookings", GET, new HttpEntity<String>(setBasicAuth("user@yandex.ru", "password")), Booking[].class, emptyMap());
        LOGGER.debug(bookingsEntity.toString());
        assertEquals(OK, bookingsEntity.getStatusCode());
        assertEquals(singletonList(BOOKING_1), asList(bookingsEntity.getBody()));
    }

    @Test
    public void testSave() {
        ResponseEntity<Booking> responseEntity = restTemplate.exchange("/bookings/the_table", POST, new HttpEntity<String>(bookingJson, setBasicAuth("user@yandex.ru", "password")), Booking.class, emptyMap());
        LOGGER.debug(responseEntity.toString());
        assertEquals(CREATED, responseEntity.getStatusCode());
        assertEquals(NEW_BOOKING_SAVED, responseEntity.getBody());
    }

    @Test
    public void testSaveNotAuthenticated() {
        ResponseEntity<Void> responseEntity = restTemplate.exchange("/bookings/the_table", POST, new HttpEntity<String>(bookingJson, setBasicAuth("not_existing_user@yandex.ru", "password")), Void.class, emptyMap());
        assertEquals(UNAUTHORIZED, responseEntity.getStatusCode());
    }

    @Test
    public void testSaveNotValidEmptyDate() {
        ResponseEntity<ErrorInfo> responseEntity = restTemplate.exchange("/bookings/the_table", POST, new HttpEntity<String>(bookingJsonNotValid, setBasicAuth("user@yandex.ru", "password")), ErrorInfo.class, emptyMap());
        LOGGER.debug(responseEntity.toString());
        assertEquals(BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("WrongBookingException", responseEntity.getBody().getCause());
    }

    @Test
    public void testSaveNotValidTooLate() {
        ResponseEntity<ErrorInfo> responseEntity = restTemplate.exchange("/bookings/aizle", POST, new HttpEntity<String>(bookingJsonNotValidToLate, setBasicAuth("user@yandex.ru", "password")), ErrorInfo.class, emptyMap());
        LOGGER.debug(responseEntity.toString());
        assertEquals(BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("WrongBookingException", responseEntity.getBody().getCause());
    }

    @Test
    public void testDelete() {
        ResponseEntity<Void> responseEntity = restTemplate.exchange("/bookings?date=2016-12-28", DELETE, new HttpEntity<String>(setBasicAuth("user@yandex.ru", "password")), Void.class, emptyMap());
        assertEquals(OK, responseEntity.getStatusCode());
    }
}