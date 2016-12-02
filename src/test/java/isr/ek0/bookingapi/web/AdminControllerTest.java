package isr.ek0.bookingapi.web;

import isr.ek0.bookingapi.model.Booking;
import isr.ek0.bookingapi.model.Restaurant;
import isr.ek0.bookingapi.model.User;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import static com.google.common.collect.ImmutableList.of;
import static isr.ek0.bookingapi.testutils.BookingUtil.BOOKINGS;
import static isr.ek0.bookingapi.testutils.JsonUtil.restaurantJson;
import static isr.ek0.bookingapi.testutils.RestaurantUtil.*;
import static isr.ek0.bookingapi.testutils.UsersUtil.USERS;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyMap;
import static org.junit.Assert.assertEquals;
import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;


public class AdminControllerTest extends BaseControllerTest {

    @Test
    public void testGetAllUsers() {
        ResponseEntity<User[]> usersEntity = restTemplate.getForEntity("/admin/users", User[].class);
        LOGGER.debug(usersEntity.toString());
        assertEquals(OK, usersEntity.getStatusCode());
        assertEquals(USERS, asList(usersEntity.getBody()));
    }

    @Test
    public void testGetByRestaurantsName() {
        ResponseEntity<Booking[]> bookingsEntity = restTemplate.getForEntity("/admin/restaurants/the_table/bookings?date=2016-12-08", Booking[].class, emptyMap());
        LOGGER.debug(bookingsEntity.toString());
        assertEquals(OK, bookingsEntity.getStatusCode());
        assertEquals(BOOKINGS, asList(bookingsEntity.getBody()));
    }

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

    @Test
    public void testSaveMeal() {
        ResponseEntity<Restaurant> responseEntity = restTemplate.postForEntity("/admin/restaurants/the_table/meals", NEW_MEAL_1, Restaurant.class, emptyMap());
        LOGGER.debug(responseEntity.toString());
        assertEquals(CREATED, responseEntity.getStatusCode());
        assertEquals(MEALS_WITH_1_NEW, responseEntity.getBody().getMenu());
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