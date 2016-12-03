package isr.ek0.bookingapi.web.admin;

import isr.ek0.bookingapi.model.Restaurant;
import isr.ek0.bookingapi.util.exception.ErrorInfo;
import isr.ek0.bookingapi.web.BaseControllerTest;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;

import static com.google.common.collect.ImmutableList.of;
import static isr.ek0.bookingapi.testutils.JsonUtil.restaurantJson;
import static isr.ek0.bookingapi.testutils.JsonUtil.restaurantJsonNotValid;
import static isr.ek0.bookingapi.testutils.RestaurantUtil.*;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyMap;
import static org.junit.Assert.assertEquals;
import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpStatus.*;

public class AdminRestaurantControllerTest extends BaseControllerTest {

    @Test
    public void testSaveRestaurant() {
        ResponseEntity<Restaurant> responseEntity = restTemplate.exchange("/admin/restaurants", POST, new HttpEntity<String>(restaurantJson, setBasicAuth("admin@gmail.com", "admin")), Restaurant.class, emptyMap());
        LOGGER.debug(responseEntity.toString());
        assertEquals(CREATED, responseEntity.getStatusCode());
        assertEquals(NEW_RESTAURANT, responseEntity.getBody());
    }

    @Test
    public void testSaveRestaurantNotValid() {
        ResponseEntity<ErrorInfo> responseEntity = restTemplate.exchange("/admin/restaurants", POST, new HttpEntity<String>(restaurantJsonNotValid, setBasicAuth("admin@gmail.com", "admin")), ErrorInfo.class, emptyMap());
        LOGGER.debug(responseEntity.toString());
        assertEquals(BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("ValidationException", responseEntity.getBody().getCause());
    }

    @Test
    public void testDeleteRestaurant() {
        ResponseEntity<Void> responseEntity = restTemplate.exchange("/admin/restaurants/the_table", DELETE, new HttpEntity<String>(setBasicAuth("admin@gmail.com", "admin")), Void.class, emptyMap());
        assertEquals(OK, responseEntity.getStatusCode());
    }

    @Test
    public void testDeleteRestaurantForbidden() {
        ResponseEntity<Void> responseEntity = restTemplate.exchange("/admin/restaurants/the_table", DELETE, new HttpEntity<String>(setBasicAuth("user@yandex.ru", "password")), Void.class, emptyMap());
        assertEquals(FORBIDDEN, responseEntity.getStatusCode());
    }

    @Test
    public void testDeleteRestaurantUnauthorized() {
        ResponseEntity<Void> responseEntity = restTemplate.exchange("/admin/restaurants/the_table", DELETE, null, Void.class, emptyMap());
        assertEquals(UNAUTHORIZED, responseEntity.getStatusCode());
    }

    @Test
    public void testGetOwnRestaurants() {
        ResponseEntity<Restaurant[]> responseEntity = restTemplate.exchange("/admin/restaurants", GET, new HttpEntity<String>(setBasicAuth("admin@gmail.com", "admin")), Restaurant[].class, emptyMap());
        LOGGER.debug(responseEntity.toString());
        assertEquals(OK, responseEntity.getStatusCode());
        assertEquals(of(ADMIN1_RESTAURANT1, ADMIN1_RESTAURANT2), asList(responseEntity.getBody()));
    }
}
