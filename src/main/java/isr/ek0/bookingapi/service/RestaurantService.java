package isr.ek0.bookingapi.service;

import isr.ek0.bookingapi.model.Meal;
import isr.ek0.bookingapi.model.Restaurant;
import isr.ek0.bookingapi.to.RestaurantWithDistance;
import lombok.NonNull;

import java.util.List;

public interface RestaurantService {

    Restaurant save(String email, Restaurant restaurant);

    Restaurant get(String name);

    void delete(String loggedUserName, String restaurantName);

    List<Restaurant> getAll(String sort);

    List<RestaurantWithDistance> getAllByLocationAndDistance(String longitude, String latitude, String distanceKm);

    List<Restaurant> getAllByOwnerEmail(String ownerEmail);

    Restaurant getRestaurantForOwner(String loggedAdminEmail, String restaurantName);

    Restaurant saveMeal(String loggedUserEmail, String restaurantName, Meal meal);

    Restaurant deleteMeal(@NonNull String loggedUserEmail, String restaurantName, String mealDescription);

    void deleteAllMeals(String loggedUserEmail, String restaurantName);

    void evictCache();
}
