package isr.ek0.bookingapi.web.admin;

import isr.ek0.bookingapi.model.Restaurant;
import isr.ek0.bookingapi.util.exception.ErrorInfo;
import isr.ek0.bookingapi.web.BaseControllerTest;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;

import static isr.ek0.bookingapi.testutils.JsonUtil.newMeal;
import static isr.ek0.bookingapi.testutils.JsonUtil.newMealNotValid;
import static isr.ek0.bookingapi.testutils.RestaurantUtil.*;
import static java.util.Collections.emptyMap;
import static org.junit.Assert.assertEquals;
import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpStatus.*;

public class AdminMealControllerTest extends BaseControllerTest {

    @Test
    public void testSaveMeal() {
        ResponseEntity<Restaurant> responseEntity = restTemplate.exchange("/admin/restaurants/the_table/meals", POST, new HttpEntity<String>(newMeal, setBasicAuth("admin@gmail.com", "admin")), Restaurant.class, emptyMap());
        LOGGER.debug(responseEntity.toString());
        assertEquals(CREATED, responseEntity.getStatusCode());
        assertEquals(MEALS_WITH_1_NEW, responseEntity.getBody().getMenu());
    }

    @Test
    public void testSaveMealNotValid() {
        ResponseEntity<ErrorInfo> responseEntity = restTemplate.exchange("/admin/restaurants/the_table/meals", POST, new HttpEntity<String>(newMealNotValid, setBasicAuth("admin@gmail.com", "admin")), ErrorInfo.class, emptyMap());
        LOGGER.debug(responseEntity.toString());
        assertEquals(BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("ValidationException", responseEntity.getBody().getCause());
    }

    @Test
    public void testDeleteMeal() {
        ResponseEntity<Void> entity = restTemplate.exchange("/admin/restaurants/aizle/meals?description=meshed potatoes", DELETE, new HttpEntity<String>(setBasicAuth("admin@gmail.com", "admin")), Void.class, emptyMap());
        assertEquals(OK, entity.getStatusCode());
    }

    @Test
    public void testDeleteRestaurantUnauthorized() {
        ResponseEntity<Void> responseEntity = restTemplate.exchange("/admin/restaurants/aizle/meals?description=meshed potatoes", DELETE, null, Void.class, emptyMap());
        assertEquals(UNAUTHORIZED, responseEntity.getStatusCode());
    }

    @Test
    public void testDeleteAllMeals() {
        ResponseEntity<Void> entity = restTemplate.exchange("/admin/restaurants/the_table/meals", DELETE, new HttpEntity<String>(setBasicAuth("admin@gmail.com", "admin")), Void.class, emptyMap());
        assertEquals(OK, entity.getStatusCode());
    }
}
