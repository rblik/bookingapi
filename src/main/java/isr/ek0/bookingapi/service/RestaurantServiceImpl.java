package isr.ek0.bookingapi.service;

import isr.ek0.bookingapi.model.Meal;
import isr.ek0.bookingapi.model.Restaurant;
import isr.ek0.bookingapi.repo.RestaurantRepository;
import isr.ek0.bookingapi.to.RestaurantWithDistance;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

import static isr.ek0.bookingapi.util.exception.ValidationUtil.checkNotFound;
import static isr.ek0.bookingapi.util.geo.GeoUtil.parseCoordinates;
import static isr.ek0.bookingapi.util.geo.GeoUtil.parseDistance;
import static org.springframework.util.Assert.notNull;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private BookingService bookingService;

    @CacheEvict(value = "restaurants", allEntries = true)
    @Override
    public Restaurant save(String loggedAdminEmail, Restaurant restaurant) {
        notNull(restaurant, "restaurant must not be null");
        restaurant.setOwnerEmail(loggedAdminEmail);
        restaurant.setName(restaurant.getName().toLowerCase().replace(" ", "_"));
        return restaurantRepository.save(loggedAdminEmail, restaurant);
    }

    @Cacheable("restaurants")
    @Override
    public Restaurant get(String name) {
        return checkNotFound(restaurantRepository.get(name), name, Restaurant.class);
    }

    @CacheEvict(value = "restaurants", allEntries = true)
    @Override
    public void delete(@NonNull String loggedAdminName, String restaurantName) {
        checkNotFound(restaurantRepository.delete(loggedAdminName, restaurantName), restaurantName, Restaurant.class);
        bookingService.deleteAllByRestaurant(restaurantName);
    }

    @Cacheable("restaurants")
    @Override
    public List<Restaurant> getAll(String sort) {
        return restaurantRepository.getAll(sort);
    }

    @Cacheable("restaurants")
    @Override
    public List<RestaurantWithDistance> getAllByLocationAndDistance(String longitude, String latitude, String distance) {
        return restaurantRepository.getAllByLocationAndDistance(parseCoordinates(longitude, latitude), parseDistance(distance));
    }

    @Override
    public List<Restaurant> getAllByOwnerEmail(@NonNull String loggedAdminEmail) {
        return restaurantRepository.getAllByOwnerEmail(loggedAdminEmail);
    }

    @Override
    public Restaurant getRestaurantForOwner(@NonNull String loggedAdminEmail, String restaurantName) {
        return checkNotFound(restaurantRepository.getRestaurantForOwner(loggedAdminEmail, restaurantName), restaurantName, loggedAdminEmail, Restaurant.class);
    }

    @CacheEvict(value = "restaurants", allEntries = true)
    @Override
    public Restaurant saveMeal(@NonNull String loggedAdminEmail, String restaurantName, Meal meal) {
        notNull(meal, "meal must not be null");
        return checkNotFound(restaurantRepository.saveMeal(loggedAdminEmail, restaurantName, meal), restaurantName, Restaurant.class);
    }

    @CacheEvict(value = "restaurants", allEntries = true)
    @Override
    public Restaurant deleteMeal(@NonNull String loggedAdminEmail, String restaurantName, String mealDescription) {
        return checkNotFound(restaurantRepository.deleteMeal(loggedAdminEmail, restaurantName, mealDescription), restaurantName, Restaurant.class);
    }

    @CacheEvict(value = "restaurants", allEntries = true)
    @Override
    public void deleteAllMeals(@NonNull String loggedUserEmail, String restaurantName) {
        checkNotFound(restaurantRepository.deleteAllMeals(loggedUserEmail, restaurantName), restaurantName, Restaurant.class, true);
    }

    @CacheEvict(value = "restaurants", allEntries = true)
    @Override
    public void evictCache() {
    }
}
