package isr.ek0.bookingapi.web;

import isr.ek0.bookingapi.model.Restaurant;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import static isr.ek0.bookingapi.testutils.RestaurantUtil.*;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.springframework.http.HttpStatus.OK;

public class RestaurantControllerTest extends BaseControllerTest{

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
}