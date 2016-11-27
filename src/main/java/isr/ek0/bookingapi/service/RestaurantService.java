package isr.ek0.bookingapi.service;

import isr.ek0.bookingapi.model.Meal;
import isr.ek0.bookingapi.model.Restaurant;
import isr.ek0.bookingapi.to.RestaurantWithDistance;

import java.util.List;

public interface RestaurantService {

    void save(String email, Restaurant restaurant);

    Restaurant get(String name);

    List<Restaurant> getAll(String sort);

    List<Restaurant> getAll();

    List<RestaurantWithDistance> getAllByLocation(List<String> coordinates);

    List<RestaurantWithDistance> getAllByLocationAndDistance(List<String> coordinates, String distanceKm);

    List<Restaurant> getAllByOwnerEmail(String ownerEmail);

    void saveMeal(String loggedUserEmail, String restaurantName, Meal meal);

    void saveMeals(String loggedUserEmail, String restaurantName, Meal... meals);

    void deleteAllMeals(String loggedUserEmail, String restaurantName);
}
