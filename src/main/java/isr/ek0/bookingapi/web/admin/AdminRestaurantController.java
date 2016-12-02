package isr.ek0.bookingapi.web.admin;

import isr.ek0.bookingapi.model.Restaurant;
import isr.ek0.bookingapi.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static isr.ek0.bookingapi.AuthorizedUser.logged_admin_email;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/admin/restaurants")
public class AdminRestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping
    public List<Restaurant> getOwnRestaurants() {
        return restaurantService.getAllByOwnerEmail(logged_admin_email);
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> saveRestaurant(@Valid @RequestBody Restaurant restaurant) {
        Restaurant restaurantCreated = restaurantService.save(logged_admin_email, restaurant);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/admin/restaurants/{restaurantName}")
                .buildAndExpand(restaurant.getName()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(restaurantCreated);
    }

    @DeleteMapping("/{restaurantName}")
    public void deleteRestaurant(@PathVariable String restaurantName) {
        restaurantService.delete(logged_admin_email, restaurantName);
    }
}
