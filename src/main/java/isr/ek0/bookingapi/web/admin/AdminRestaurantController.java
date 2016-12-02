package isr.ek0.bookingapi.web.admin;

import isr.ek0.bookingapi.model.Restaurant;
import isr.ek0.bookingapi.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static isr.ek0.bookingapi.AuthorizedUser.logged_admin_email;

@RestController
@RequestMapping("/admin/restaurants")
public class AdminRestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping
    public List<Restaurant> getOwnRestaurants() {
        return restaurantService.getAllByOwnerEmail(logged_admin_email);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Restaurant saveRestaurant(@Valid @RequestBody Restaurant restaurant) {
        return restaurantService.save(logged_admin_email, restaurant);
    }

    @DeleteMapping("/{restaurantName}")
    public void deleteRestaurant(@PathVariable String restaurantName) {
        restaurantService.delete(logged_admin_email, restaurantName);
    }
}
