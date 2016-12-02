package isr.ek0.bookingapi.web.admin;

import isr.ek0.bookingapi.model.Restaurant;
import isr.ek0.bookingapi.util.exception.ErrorInfo;
import isr.ek0.bookingapi.web.BaseControllerTest;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import static com.google.common.collect.ImmutableList.of;
import static isr.ek0.bookingapi.testutils.JsonUtil.restaurantJson;
import static isr.ek0.bookingapi.testutils.JsonUtil.restaurantJsonNotValid;
import static isr.ek0.bookingapi.testutils.RestaurantUtil.ADMIN1_RESTAURANT1;
import static isr.ek0.bookingapi.testutils.RestaurantUtil.ADMIN1_RESTAURANT2;
import static isr.ek0.bookingapi.testutils.RestaurantUtil.NEW_RESTAURANT;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyMap;
import static org.junit.Assert.assertEquals;
import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;

public class AdminRestaurantControllerTest extends BaseControllerTest {

    @Test
    public void testSaveRestaurant() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(APPLICATION_JSON_UTF8);
        HttpEntity<String> restaurantEntity = new HttpEntity<>(restaurantJson, headers);
        ResponseEntity<Restaurant> responseEntity = restTemplate.exchange("/admin/restaurants", POST, restaurantEntity, Restaurant.class, emptyMap());
        LOGGER.debug(responseEntity.toString());
        assertEquals(CREATED, responseEntity.getStatusCode());
        assertEquals(NEW_RESTAURANT, responseEntity.getBody());
    }

    @Test
    public void testSaveRestaurantNotValid() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(APPLICATION_JSON_UTF8);
        HttpEntity<String> restaurantEntity = new HttpEntity<>(restaurantJsonNotValid, headers);
        ResponseEntity<ErrorInfo> responseEntity = restTemplate.exchange("/admin/restaurants", POST, restaurantEntity, ErrorInfo.class, emptyMap());
        LOGGER.debug(responseEntity.toString());
        assertEquals(BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("ValidationException", responseEntity.getBody().getCause());
    }

    @Test
    public void testDeleteRestaurant() {
        ResponseEntity<Void> entity = restTemplate.exchange("/admin/restaurants/the_table", DELETE, null, Void.class, emptyMap());
        assertEquals(OK, entity.getStatusCode());
    }

    @Test
    public void testGetOwnRestaurants() {
        ResponseEntity<Restaurant[]> responseEntity = restTemplate.getForEntity("/admin/restaurants", Restaurant[].class, emptyMap());
        LOGGER.debug(responseEntity.toString());
        assertEquals(OK, responseEntity.getStatusCode());
        assertEquals(of(ADMIN1_RESTAURANT1, ADMIN1_RESTAURANT2), asList(responseEntity.getBody()));
    }
}
