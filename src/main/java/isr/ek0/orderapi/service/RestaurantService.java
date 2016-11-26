package isr.ek0.orderapi.service;

import isr.ek0.orderapi.model.Meal;
import isr.ek0.orderapi.model.Restaurant;
import isr.ek0.orderapi.to.RestaurantWithDistance;

import java.util.List;

public interface RestaurantService {

    void save(String email, Restaurant restaurant);

    List<Restaurant> getAll(String sort);

    List<Restaurant> getAll();

    List<RestaurantWithDistance> getAllByLocation(List<String> coordinates);

    List<RestaurantWithDistance> getAllByLocationAndDistance(List<String> coordinates, String distanceKm);

    List<Restaurant> getAllByOwnerEmail(String ownerEmail);

    void saveMeal(String loggedUserEmail, String restaurantName, Meal meal);

    void saveMeals(String loggedUserEmail, String restaurantName, Meal... meals);

    void deleteAllMeals(String loggedUserEmail, String restaurantName);
}
