package isr.ek0.bookingapi.repo;

import isr.ek0.bookingapi.model.Meal;
import isr.ek0.bookingapi.model.Restaurant;
import isr.ek0.bookingapi.to.RestaurantWithDistance;

import java.util.List;

public interface RestaurantRepository {

    void save(String loggedUserEmail, Restaurant restaurant);

    List<Restaurant> getAll(String sort);

    List<RestaurantWithDistance> getAllByLocation(List<Double> coordinates);

    List<RestaurantWithDistance> getAllByLocationAndDistance(List<Double> coordinates, Double maxDistance);

    List<Restaurant> getAllByOwnerEmail(String ownerEmail);

    void saveMeal(String loggedUserEmail, String restaurantName, Meal meal);

    void saveMeals(String loggedUserEmail, String restaurantName, Meal... meals);

    void deleteAllMeals(String loggedUserEmail, String restaurantName);
}
