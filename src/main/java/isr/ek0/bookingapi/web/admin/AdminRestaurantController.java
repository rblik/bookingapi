package isr.ek0.bookingapi.web.admin;

import isr.ek0.bookingapi.AuthorizedUser;
import isr.ek0.bookingapi.model.Restaurant;
import isr.ek0.bookingapi.service.RestaurantService;
import isr.ek0.bookingapi.web.user.RestaurantController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/admin/restaurants")
public class AdminRestaurantController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdminRestaurantController.class);

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping
    public List<Restaurant> getOwnRestaurants() {
        String loggedAdminEmail = AuthorizedUser.mail();
        LOGGER.info("admin {} is retrieving all his restaurants", loggedAdminEmail);
        List<Restaurant> allByOwnerEmail = restaurantService.getAllByOwnerEmail(loggedAdminEmail);
        return allByOwnerEmail.stream().peek(restaurant -> restaurant.add(
                linkTo(RestaurantController.class).slash(restaurant.getName()).withSelfRel(),
                linkTo(methodOn(AdminBookingController.class).getAllBookingsByRestaurant(restaurant.getName(), null)).withRel("bookings")))
                .collect(toList());
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> saveRestaurant(@Valid @RequestBody Restaurant restaurant) {
        String loggedAdminName = AuthorizedUser.mail();
        LOGGER.info("admin {} is saving new {}", loggedAdminName, restaurant);
        Restaurant restaurantCreated = restaurantService.save(loggedAdminName, restaurant);
        restaurantCreated.add(linkTo(RestaurantController.class).slash(restaurant.getName()).withSelfRel());
        restaurantCreated.add(linkTo(AdminRestaurantController.class).withRel("ownRestaurants"));
        restaurantCreated.add(linkTo(methodOn(AdminBookingController.class).getAllBookingsByRestaurant(restaurant.getName(), null)).withRel("bookings"));
        return ResponseEntity.status(CREATED).body(restaurantCreated);
    }

    @DeleteMapping("/{restaurantName}")
    public void deleteRestaurant(@PathVariable String restaurantName) {
        String loggedAdminName = AuthorizedUser.mail();
        LOGGER.info("admin {} is deleting {}", loggedAdminName, restaurantName);
        restaurantService.delete(loggedAdminName, restaurantName);
    }
}
