package isr.ek0.bookingapi.web.user;

import isr.ek0.bookingapi.model.Restaurant;
import isr.ek0.bookingapi.util.exception.ErrorInfo;
import isr.ek0.bookingapi.web.BaseControllerTest;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import static isr.ek0.bookingapi.testutils.RestaurantUtil.*;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

public class RestaurantControllerTest extends BaseControllerTest {

    @Test
    public void testGetAll() {
        ResponseEntity<Restaurant[]> restaurantsEntity = restTemplate.getForEntity("/restaurants", Restaurant[].class);
        LOGGER.debug(restaurantsEntity.toString());
        assertEquals(OK, restaurantsEntity.getStatusCode());
        assertEquals(RESTAURANTS_SORTED_BY_NAME, asList(restaurantsEntity.getBody()));
    }
    @Test
    public void testGetAllDescending() {
        ResponseEntity<Restaurant[]> restaurantsEntity = restTemplate.getForEntity("/restaurants?order=desc", Restaurant[].class);
        LOGGER.debug(restaurantsEntity.toString());
        assertEquals(OK, restaurantsEntity.getStatusCode());
        assertEquals(RESTAURANTS_SORTED_BY_NAME_REVERSED, asList(restaurantsEntity.getBody()));
    }

    @Test
    public void testGetByRestaurantName() {
        ResponseEntity<Restaurant> restaurantsEntity = restTemplate.getForEntity("/restaurants/the_table", Restaurant.class);
        LOGGER.debug(restaurantsEntity.toString());
        assertEquals(OK, restaurantsEntity.getStatusCode());
        assertEquals(ADMIN1_RESTAURANT1, restaurantsEntity.getBody());
    }

    @Test
    public void testGetByRestaurantNameNotFound() {
        ResponseEntity<ErrorInfo> errorEntity = restTemplate.getForEntity("/restaurants/not_existing_restaurant", ErrorInfo.class);
        LOGGER.debug(errorEntity.toString());
        assertEquals(UNPROCESSABLE_ENTITY, errorEntity.getStatusCode());
        assertEquals("NotFoundException", errorEntity.getBody().getCause());
    }
}