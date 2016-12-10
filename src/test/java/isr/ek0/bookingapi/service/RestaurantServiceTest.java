package isr.ek0.bookingapi.service;

import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import isr.ek0.bookingapi.model.Restaurant;
import isr.ek0.bookingapi.to.RestaurantWithDistance;
import isr.ek0.bookingapi.util.exception.NotFoundException;
import isr.ek0.bookingapi.util.exception.WrongCoordinatesException;
import org.junit.Test;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.mongodb.core.query.Query;

import javax.validation.ConstraintViolationException;
import java.util.List;

import static com.google.common.collect.ImmutableList.of;
import static isr.ek0.bookingapi.testutils.RestaurantData.*;
import static isr.ek0.bookingapi.testutils.UserData.ADMIN_1;
import static isr.ek0.bookingapi.testutils.UserData.ADMIN_2;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertEquals;
import static org.springframework.data.mongodb.core.query.Criteria.where;

public class RestaurantServiceTest extends BaseServiceTest{

    @Test
    public void testGet() {
        Restaurant restaurant = restaurantService.get(ADMIN1_RESTAURANT1.getName());
        assertEquals(ADMIN1_RESTAURANT1, restaurant);
    }

    @Test(expected = NotFoundException.class)
    public void testGetNotFound() {
        restaurantService.get("Not Existing Restaurant");
    }

    @Test
    public void testSave() {
        restaurantService.save(ADMIN_1.getEmail(), NEW_RESTAURANT);
        assertEquals(RESTAURANTS_WITH_NEW, restaurantService.getAll(null));
    }

    @Test(expected = ConstraintViolationException.class)
    public void testSaveNotValid() {
        restaurantService.save(ADMIN_1.getEmail(), NEW_RESTAURANT_NOT_VALID);
        assertEquals(RESTAURANTS_WITH_NEW, restaurantService.getAll(null));
    }

    @Test(expected = ConstraintViolationException.class)
    public void testSaveWithNotValidMeal() {
        restaurantService.save(ADMIN_1.getEmail(), NEW_RESTAURANT_NOT_VALID_MEAL);
    }

    @Test(expected = DuplicateKeyException.class)
    public void testSaveWithDuplicateName() {
        restaurantService.save(ADMIN_1.getEmail(), NEW_RESTAURANT_DUPLICATE);
    }

    @Test
    public void testDelete() {
        restaurantService.delete(ADMIN_1.getEmail(), ADMIN1_RESTAURANT1.getName());
        assertEquals(singletonList(ADMIN1_RESTAURANT2), restaurantService.getAllByOwnerEmail(ADMIN_1.getEmail()));
    }

    @Test(expected = NotFoundException.class)
    public void testDeleteNotFound() {
        restaurantService.delete(ADMIN_2.getEmail(), ADMIN1_RESTAURANT1.getName());
    }

    @Test
    public void testGetAll() {
        List<Restaurant> all = restaurantService.getAll(null);
        assertEquals(RESTAURANTS_SORTED_BY_NAME, all);
    }

    @Test
    public void testAllByLocation() {
        List<RestaurantWithDistance> allByLocation = restaurantService.getAllByLocationAndDistance("0", "0", null);
        assertEquals(RESTAURANTS_SORTED_BY_LOCATION, allByLocation);
    }

    @Test(expected = WrongCoordinatesException.class)
    public void testAllByLocationWithWrongCoordinates() {
        restaurantService.getAllByLocationAndDistance("wrong value", "another wrong value", null);
    }

    @Test
    public void testGetLocationWithDistance() {
        List<RestaurantWithDistance> allByLocationWithDistance = restaurantService.getAllByLocationAndDistance("0", "0", "5500.0");
        assertEquals(singletonList(ADMIN2_RESTAURANT2), allByLocationWithDistance);
    }

    @Test
    public void testGetLocationWithWrongDistance() {
        List<RestaurantWithDistance> allByLocationWithDistance = restaurantService.getAllByLocationAndDistance("0", "0", "wrong value");
        assertEquals(RESTAURANTS_SORTED_BY_LOCATION, allByLocationWithDistance);
    }

    @Test
    public void testGetByOwner() {
        List<Restaurant> allByOwnerEmail = restaurantService.getAllByOwnerEmail(ADMIN_1.getEmail());
        assertThat(allByOwnerEmail, containsInAnyOrder(ADMIN1_RESTAURANT1, ADMIN1_RESTAURANT2));
    }

//    @Test disabled for travis-ci
    public void testQueryPlan() {
        Query query = new Query(where("ownerEmail").is(ADMIN_1.getEmail()));
        DBCollection collection = mongoTemplate.getCollection("restaurants");
        DBCursor cursor = collection.find(query.getQueryObject());
        LOGGER.debug(cursor.explain().toString());
        DBObject queryPlanner = (DBObject)cursor.explain().get("queryPlanner");
        DBObject winningPlan = (DBObject)queryPlanner.get("winningPlan");
        DBObject inputStage = (DBObject)winningPlan.get("inputStage");
        assertEquals("IXSCAN", inputStage.get("stage"));
    }

    @Test
    public void testSaveMeal() {
        restaurantService.saveMeal(ADMIN_1.getEmail(), ADMIN1_RESTAURANT1.getName(), NEW_MEAL_1);
        assertEquals(MEALS_WITH_1_NEW ,restaurantService.get(ADMIN1_RESTAURANT1.getName()).getMenu());
    }

    @Test(expected = ConstraintViolationException.class)
    public void testSaveNotValidMeal() {
        restaurantService.saveMeal(ADMIN_1.getEmail(), ADMIN1_RESTAURANT1.getName(), NEW_RESTAURANT_MEAL_3_NOT_VALID);
    }

    @Test(expected = NotFoundException.class)
    public void testSaveMealWithWrongRestaurant() {
        restaurantService.saveMeal(ADMIN_1.getEmail(), ADMIN2_RESTAURANT2.getName(), NEW_MEAL_1);
    }

    @Test
    public void testDeleteMealByDescription() {
        restaurantService.deleteMeal(ADMIN_1.getEmail(), ADMIN1_RESTAURANT1.getName(), A_1_R_1_MEAL_1.getDescription());
        assertEquals(of(A_1_R_1_MEAL_2, A_1_R_1_MEAL_3), restaurantService.get(ADMIN1_RESTAURANT1.getName()).getMenu());
    }

    @Test(expected = NotFoundException.class)
    public void testDeleteMealByDescriptionWithWrongRestaurant() {
        restaurantService.deleteMeal(ADMIN_1.getEmail(), ADMIN2_RESTAURANT1.getName(), A_2_R_1_MEAL_1.getDescription());
    }

    @Test
    public void testDeleteMeals() {
        restaurantService.deleteAllMeals(ADMIN_1.getEmail(), ADMIN1_RESTAURANT1.getName());
        assertEquals(emptyList(), restaurantService.get(ADMIN1_RESTAURANT1.getName()).getMenu());
    }

    @Test(expected = NotFoundException.class)
    public void testDeleteMealsWithWrongRestaurant() {
        restaurantService.deleteAllMeals(ADMIN_2.getEmail(), ADMIN1_RESTAURANT1.getName());
    }

    @Test
    public void testDeleteAndSaveMeal() {
        restaurantService.deleteAllMeals(ADMIN_1.getEmail(), ADMIN1_RESTAURANT1.getName());
        restaurantService.saveMeal(ADMIN_1.getEmail(), ADMIN1_RESTAURANT1.getName(), NEW_MEAL_1);
        assertEquals(singletonList(NEW_MEAL_1), restaurantService.get(ADMIN1_RESTAURANT1.getName()).getMenu());
    }
}