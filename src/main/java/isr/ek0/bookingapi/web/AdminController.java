package isr.ek0.bookingapi.web;

import isr.ek0.bookingapi.model.Booking;
import isr.ek0.bookingapi.model.Meal;
import isr.ek0.bookingapi.model.Restaurant;
import isr.ek0.bookingapi.model.User;
import isr.ek0.bookingapi.service.BookingService;
import isr.ek0.bookingapi.service.RestaurantService;
import isr.ek0.bookingapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static isr.ek0.bookingapi.AuthorizedUser.logged_admin_email;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;
    @Autowired
    private BookingService bookingService;
    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAll();
    }

    @GetMapping("/restaurants/{restaurantName}/bookings")
    public List<Booking> getAllBookingsByRestaurant(@PathVariable String restaurantName) {
        return bookingService.getAllByRestaurantName(logged_admin_email, restaurantName);
    }

    @GetMapping("/restaurants")
    public List<Restaurant> getOwnRestaurants() {
        return restaurantService.getAllByOwnerEmail(logged_admin_email);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, value = "/restaurants")
    public Restaurant saveRestaurant(@RequestBody Restaurant restaurant) {
        return restaurantService.save(logged_admin_email, restaurant);
    }

    @DeleteMapping("/restaurants/{restaurantName}")
    public void deleteRestaurant(@PathVariable String restaurantName) {
        restaurantService.delete(logged_admin_email, restaurantName);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, value = "/restaurants/{restaurantName}/meal")
    public Restaurant saveMeal(@RequestBody Meal meal, @PathVariable String restaurantName) {
        return restaurantService.saveMeal(logged_admin_email, restaurantName, meal);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, value = "/restaurants/{restaurantName}/meals")
    public Restaurant saveMeals(@RequestBody List<Meal> meals, @PathVariable String restaurantName) {
        return restaurantService.saveMeals(logged_admin_email, restaurantName, meals);
    }

    @DeleteMapping("/restaurants/{restaurantName}/meals")
    public void deleteMeals(@PathVariable String restaurantName) {
        restaurantService.deleteAllMeals(logged_admin_email, restaurantName);
    }

    // TODO: 30.11.2016 PRE-AUTHORIZE GETUSER
}
