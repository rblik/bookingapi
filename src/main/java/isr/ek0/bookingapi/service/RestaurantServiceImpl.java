package isr.ek0.bookingapi.service;

import isr.ek0.bookingapi.model.Meal;
import isr.ek0.bookingapi.model.Restaurant;
import isr.ek0.bookingapi.repo.BookingRepository;
import isr.ek0.bookingapi.repo.RestaurantRepository;
import isr.ek0.bookingapi.to.RestaurantWithDistance;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

import static isr.ek0.bookingapi.util.exception.ExceptionUtil.checkNotFound;
import static isr.ek0.bookingapi.util.geo.GeoUtil.parseCoordinates;
import static isr.ek0.bookingapi.util.geo.GeoUtil.parseDistance;
import static org.springframework.util.Assert.noNullElements;
import static org.springframework.util.Assert.notNull;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private BookingRepository bookingRepository;

    @CacheEvict(value = "restaurants", allEntries = true)
    @Override
    public void save(String loggedUserEmail, Restaurant restaurant) {
        notNull(restaurant, "restaurant must not be null");
        restaurant.setOwnerEmail(loggedUserEmail);
        restaurantRepository.save(loggedUserEmail, restaurant);
    }

    @Cacheable("restaurants")
    @Override
    public Restaurant get(String name) {
        return checkNotFound(restaurantRepository.get(name), name, Restaurant.class);
    }

    @CacheEvict(value = "restaurants", allEntries = true)
    @Override
    public void delete(String loggedUserName, String restaurantName) {
        checkNotFound(restaurantRepository.delete(loggedUserName, restaurantName), restaurantName, Restaurant.class);
        bookingRepository.deleteAllByRestaurant(restaurantName);
    }

    @Cacheable("restaurants")
    @Override
    public List<Restaurant> getAll(String sort) {
        return restaurantRepository.getAll(sort);
    }

    @Cacheable("restaurants")
    @Override
    public List<Restaurant> getAll() {
        return getAll("");
    }

    @Cacheable("restaurants")
    @Override
    public List<RestaurantWithDistance> getAllByLocation(List<String> coordinates) {
        return getAllByLocationAndDistance(coordinates, "max");
    }

    @Cacheable("restaurants")
    @Override
    public List<RestaurantWithDistance> getAllByLocationAndDistance(List<String> coordinates, String distance) {
        return restaurantRepository.getAllByLocationAndDistance(parseCoordinates(coordinates), parseDistance(distance));
    }

    @Override
    public List<Restaurant> getAllByOwnerEmail(@NonNull String ownerEmail) {
        return restaurantRepository.getAllByOwnerEmail(ownerEmail);
    }

    @CacheEvict(value = "restaurants", allEntries = true)
    @Override
    public void saveMeal(@NonNull String loggedUserEmail, String restaurantName, Meal meal) {
        notNull(meal, "meal must not be null");
        checkNotFound(restaurantRepository.saveMeal(loggedUserEmail, restaurantName, meal), restaurantName, Restaurant.class, true);
    }

    @CacheEvict(value = "restaurants", allEntries = true)
    @Override
    public void saveMeals(@NonNull String loggedUserEmail, String restaurantName, Meal... meals) {
        noNullElements(meals, "meals must not have null elements");
        checkNotFound(restaurantRepository.saveMeals(loggedUserEmail, restaurantName, meals), restaurantName, Restaurant.class, true);
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
