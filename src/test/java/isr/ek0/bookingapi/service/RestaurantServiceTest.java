package isr.ek0.bookingapi.service;

import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import isr.ek0.bookingapi.model.Restaurant;
import isr.ek0.bookingapi.to.RestaurantWithDistance;
import org.junit.Test;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

import static isr.ek0.bookingapi.service.testutils.RestaurantUtil.*;
import static isr.ek0.bookingapi.service.testutils.UsersUtil.ADMIN_1;
import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertEquals;

public class RestaurantServiceTest extends BaseServiceTest{

    @Test
    public void testSave() {
        restaurantService.save(ADMIN_1.getEmail(), NEW_RESTAURANT);
    }

    @Test
    public void testGet() {
        Restaurant restaurant = restaurantService.get(ADMIN1_RESTAURANT1.getName());
        System.out.println(restaurant);
    }

    @Test
    public void testGetAll() {
        List<Restaurant> all = restaurantService.getAll();
        System.out.println(all);
    }

    @Test
    public void testAllByLocation() {
        List<RestaurantWithDistance> allByLocation = restaurantService.getAllByLocation(asList("0", "0"));
        System.out.println(allByLocation);
    }

    @Test
    public void testGetLocationWithDistance() {
        List<RestaurantWithDistance> allByLocationWithDistance = restaurantService.getAllByLocationAndDistance(asList("0", "0"), "6000.0");
        System.out.println(allByLocationWithDistance);
    }

    @Test
    public void testGetByOwner() {
        List<Restaurant> allByOwnerEmail = restaurantService.getAllByOwnerEmail("admin@gmail.com");
        assertThat(allByOwnerEmail, containsInAnyOrder(ADMIN1_RESTAURANT1, ADMIN1_RESTAURANT2));
    }

    @Test
    public void testQueryPlan() {
        Query query = new Query(Criteria.where("ownerEmail").is("admin@gmail.com"));
        DBCollection collection = template.getCollection("restaurants");
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
    }

    @Test
    public void testSaveMeals() {
        restaurantService.saveMeals(ADMIN_1.getEmail(), ADMIN1_RESTAURANT1.getName(), NEW_MEAL_1, NEW_MEAL_2);
    }

    @Test
    public void testDeleteMeals() {
        restaurantService.deleteAllMeals(ADMIN_1.getEmail(), ADMIN1_RESTAURANT1.getName());
    }



    // TODO: 25.11.2016 TEST ERRORS
}