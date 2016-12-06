package isr.ek0.bookingapi.service;

import isr.ek0.bookingapi.model.Meal;
import isr.ek0.bookingapi.model.Restaurant;
import isr.ek0.bookingapi.to.RestaurantWithDistance;

import java.util.List;

public interface RestaurantService {

    Restaurant save(String loggedAdminEmail, Restaurant restaurant);

    Restaurant get(String name);

    void delete(String loggedAdminName, String restaurantName);

    List<Restaurant> getAll(String sort);

    List<RestaurantWithDistance> getAllByLocationAndDistance(String longitude, String latitude, String distanceKm);

    List<Restaurant> getAllByOwnerEmail(String loggedAdminEmail);

    Restaurant getRestaurantForOwner(String loggedAdminEmail, String restaurantName);

    Restaurant saveMeal(String loggedAdminEmail, String restaurantName, Meal meal);

    Restaurant deleteMeal(String loggedAdminEmail, String restaurantName, String mealDescription);

    void deleteAllMeals(String loggedAdminEmail, String restaurantName);

    void evictCache();
}
