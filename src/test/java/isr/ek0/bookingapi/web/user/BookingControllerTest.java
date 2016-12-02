package isr.ek0.bookingapi.web.user;

import isr.ek0.bookingapi.model.Booking;
import isr.ek0.bookingapi.util.exception.ErrorInfo;
import isr.ek0.bookingapi.web.BaseControllerTest;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import static isr.ek0.bookingapi.testutils.BookingUtil.*;
import static isr.ek0.bookingapi.testutils.JsonUtil.bookingJson;
import static isr.ek0.bookingapi.testutils.JsonUtil.bookingJsonNotValid;
import static isr.ek0.bookingapi.testutils.JsonUtil.bookingJsonNotValidToLate;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyMap;
import static java.util.Collections.singletonList;
import static org.junit.Assert.assertEquals;
import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;

public class BookingControllerTest extends BaseControllerTest {

    @Test
    public void testGetAll() {
        ResponseEntity<Booking[]> bookingsEntity = restTemplate.getForEntity("/bookings", Booking[].class, emptyMap());
        LOGGER.debug(bookingsEntity.toString());
        assertEquals(OK, bookingsEntity.getStatusCode());
        assertEquals(singletonList(BOOKING_1), asList(bookingsEntity.getBody()));
    }

    @Test
    public void testSave() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(APPLICATION_JSON_UTF8);
        HttpEntity<String> bookingEntity = new HttpEntity<>(bookingJson, headers);
        ResponseEntity<Booking> responseEntity = restTemplate.exchange("/bookings/the_table", POST, bookingEntity, Booking.class, emptyMap());
        LOGGER.debug(responseEntity.toString());
        assertEquals(CREATED, responseEntity.getStatusCode());
        assertEquals(NEW_BOOKING_SAVED, responseEntity.getBody());
    }

    @Test
    public void testSaveNotValidEmptyDate() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(APPLICATION_JSON_UTF8);
        HttpEntity<String> bookingEntity = new HttpEntity<>(bookingJsonNotValid, headers);
        ResponseEntity<ErrorInfo> responseEntity = restTemplate.exchange("/bookings/the_table", POST, bookingEntity, ErrorInfo.class, emptyMap());
        LOGGER.debug(responseEntity.toString());
        assertEquals(BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("WrongBookingException", responseEntity.getBody().getCause());
    }

    @Test
    public void testSaveNotValidTooLate() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(APPLICATION_JSON_UTF8);
        HttpEntity<String> bookingEntity = new HttpEntity<>(bookingJsonNotValidToLate, headers);
        ResponseEntity<ErrorInfo> responseEntity = restTemplate.exchange("/bookings/aizle", POST, bookingEntity, ErrorInfo.class, emptyMap());
        LOGGER.debug(responseEntity.toString());
        assertEquals(BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("WrongBookingException", responseEntity.getBody().getCause());
    }

    @Test
    public void testDelete() {
        ResponseEntity<Void> responseEntity = restTemplate.exchange("/bookings?date=2016-12-08", DELETE, null, Void.class, emptyMap());
        assertEquals(OK, responseEntity.getStatusCode());
    }
}