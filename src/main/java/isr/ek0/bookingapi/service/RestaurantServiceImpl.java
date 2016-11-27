package isr.ek0.bookingapi.service;

import isr.ek0.bookingapi.model.Meal;
import isr.ek0.bookingapi.model.Restaurant;
import isr.ek0.bookingapi.repo.RestaurantRepository;
import isr.ek0.bookingapi.to.RestaurantWithDistance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.google.common.primitives.Doubles.asList;
import static java.lang.Double.parseDouble;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Override
    public void save(String loggedUserEmail, Restaurant restaurant) {
        Restaurant save = restaurantRepository.save(loggedUserEmail, restaurant);
    }

    @Override
    public Restaurant get(String name) {
        return restaurantRepository.get(name);
    }

    @Override
    public List<Restaurant> getAll(String sort) {
        return restaurantRepository.getAll(sort);
    }

    @Override
    public List<Restaurant> getAll() {
        return getAll("");
    }

    @Override
    public List<RestaurantWithDistance> getAllByLocation(List<String> coordinates) {
        // TODO: 25.11.2016 all needed checks
        Double latitude = parseDouble(coordinates.get(0));
        Double longitude = parseDouble(coordinates.get(1));
        return restaurantRepository.getAllByLocation(asList(latitude, longitude));
    }

    @Override
    public List<RestaurantWithDistance> getAllByLocationAndDistance(List<String> coordinates, String distanceKmStr) {
        // TODO: 25.11.2016 all needed checks
        Double latitude = parseDouble(coordinates.get(0));
        Double longitude = parseDouble(coordinates.get(1));
        Double distanceKm = parseDouble(distanceKmStr);
        return restaurantRepository.getAllByLocationAndDistance(asList(latitude, longitude), distanceKm);
    }

    @Override
    public List<Restaurant> getAllByOwnerEmail(String ownerEmail) {
        return restaurantRepository.getAllByOwnerEmail(ownerEmail);
    }

    @Override
    public void saveMeal(String loggedUserEmail, String restaurantName, Meal meal) {
        restaurantRepository.saveMeal(loggedUserEmail, restaurantName, meal);
    }

    @Override
    public void saveMeals(String loggedUserEmail, String restaurantName, Meal... meals) {
        restaurantRepository.saveMeals(loggedUserEmail, restaurantName, meals);
    }

    @Override
    public void deleteAllMeals(String loggedUserEmail, String restaurantName) {
        restaurantRepository.deleteAllMeals(loggedUserEmail, restaurantName);
    }
}
