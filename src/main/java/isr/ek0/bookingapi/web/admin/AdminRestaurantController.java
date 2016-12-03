package isr.ek0.bookingapi.web.admin;

import isr.ek0.bookingapi.AuthorizedUser;
import isr.ek0.bookingapi.model.Restaurant;
import isr.ek0.bookingapi.service.RestaurantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/admin/restaurants")
public class AdminRestaurantController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdminRestaurantController.class);

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping
    public List<Restaurant> getOwnRestaurants() {
        String ownerEmail = AuthorizedUser.mail();
        LOGGER.info("admin {} is retrieving all his restaurants", ownerEmail);
        return restaurantService.getAllByOwnerEmail(ownerEmail);
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> saveRestaurant(@Valid @RequestBody Restaurant restaurant) {
        String loggedAdminName = AuthorizedUser.mail();
        Restaurant restaurantCreated = restaurantService.save(loggedAdminName, restaurant);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/admin/restaurants/{restaurantName}")
                .buildAndExpand(restaurant.getName()).toUri();
        LOGGER.info("admin {} is saving new {}", loggedAdminName, restaurant);
        return ResponseEntity.created(uriOfNewResource).body(restaurantCreated);
    }

    @DeleteMapping("/{restaurantName}")
    public void deleteRestaurant(@PathVariable String restaurantName) {
        String loggedAdminName = AuthorizedUser.mail();
        LOGGER.info("admin {} is deleting {}", loggedAdminName, restaurantName);
        restaurantService.delete(loggedAdminName, restaurantName);
    }
}
