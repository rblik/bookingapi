package isr.ek0.bookingapi.repo;

import isr.ek0.bookingapi.model.Meal;
import isr.ek0.bookingapi.model.Restaurant;
import isr.ek0.bookingapi.to.RestaurantWithDistance;

import java.util.List;

public interface RestaurantRepository {

    Restaurant save(String loggedUserEmail, Restaurant restaurant);

    Restaurant get(String name);

    Restaurant delete(String loggedUserEmail, String restaurantName);

    List<Restaurant> getAll(String sort);

    List<RestaurantWithDistance> getAllByLocationAndDistance(List<Double> coordinates, Double maxDistance);

    List<Restaurant> getAllByOwnerEmail(String ownerEmail);

    Restaurant saveMeal(String loggedUserEmail, String restaurantName, Meal meal);

    Restaurant saveMeals(String loggedUserEmail, String restaurantName, List<Meal> meals);

    int deleteAllMeals(String loggedUserEmail, String restaurantName);
}
