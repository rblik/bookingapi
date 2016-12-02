package isr.ek0.bookingapi.web.admin;

import isr.ek0.bookingapi.model.Restaurant;
import isr.ek0.bookingapi.util.exception.ErrorInfo;
import isr.ek0.bookingapi.web.BaseControllerTest;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import static isr.ek0.bookingapi.testutils.RestaurantUtil.MEALS_WITH_1_NEW;
import static isr.ek0.bookingapi.testutils.RestaurantUtil.NEW_MEAL_1;
import static isr.ek0.bookingapi.testutils.RestaurantUtil.NEW_MEAL_NOT_VALID;
import static java.util.Collections.emptyMap;
import static org.junit.Assert.assertEquals;
import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

public class AdminMealControllerTest extends BaseControllerTest {

    @Test
    public void testSaveMeal() {
        ResponseEntity<Restaurant> responseEntity = restTemplate.postForEntity("/admin/restaurants/the_table/meals", NEW_MEAL_1, Restaurant.class, emptyMap());
        LOGGER.debug(responseEntity.toString());
        assertEquals(CREATED, responseEntity.getStatusCode());
        assertEquals(MEALS_WITH_1_NEW, responseEntity.getBody().getMenu());
    }

    @Test
    public void testSaveMealNotValid() {
        ResponseEntity<ErrorInfo> responseEntity = restTemplate.postForEntity("/admin/restaurants/the_table/meals", NEW_MEAL_NOT_VALID, ErrorInfo.class, emptyMap());
        LOGGER.debug(responseEntity.toString());
        assertEquals(BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("ValidationException", responseEntity.getBody().getCause());
    }

    @Test
    public void testDeleteMeal() {
        ResponseEntity<Void> entity = restTemplate.exchange("/admin/restaurants/aizle/meals?description=meshed potatoes", DELETE, null, Void.class, emptyMap());
        assertEquals(OK, entity.getStatusCode());
    }

    @Test
    public void testDeleteAllMeals() {
        ResponseEntity<Void> entity = restTemplate.exchange("/admin/restaurants/the_table/meals", DELETE, null, Void.class, emptyMap());
        assertEquals(OK, entity.getStatusCode());
    }
}
