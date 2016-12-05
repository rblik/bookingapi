package isr.ek0.bookingapi.web.admin;

import isr.ek0.bookingapi.AuthorizedUser;
import isr.ek0.bookingapi.model.Meal;
import isr.ek0.bookingapi.model.Restaurant;
import isr.ek0.bookingapi.service.RestaurantService;
import isr.ek0.bookingapi.web.user.RestaurantController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/admin/restaurants/{restaurantName}/meals")
public class AdminMealController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdminMealController.class);

    @Autowired
    private RestaurantService service;


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> saveMeal(@Valid @RequestBody Meal meal, @PathVariable String restaurantName) {
        String loggedAdminEmail = AuthorizedUser.mail();
        Restaurant restaurant = service.saveMeal(loggedAdminEmail, restaurantName, meal);
        LOGGER.info("admin {} is saving {} for restaurant {}", loggedAdminEmail, meal, restaurantName);
        restaurant.add(linkTo(RestaurantController.class).slash(restaurant.getName()).withRel("restaurant"));
        restaurant.add(linkTo(AdminRestaurantController.class).withRel("ownRestaurants"));
        return ResponseEntity.status(CREATED).body(restaurant);
    }

    @DeleteMapping(params = {"description"})
    public void deleteMeal(@PathVariable String restaurantName, @RequestParam String description) {
        String loggedAdminEmail = AuthorizedUser.mail();
        LOGGER.info("admin {} is deleting {} for restaurant {}", loggedAdminEmail, description, restaurantName);
        service.deleteMeal(loggedAdminEmail, restaurantName, description);
    }

    @DeleteMapping
    public void deleteMeals(@PathVariable String restaurantName) {
        String loggedAdminEmail = AuthorizedUser.mail();
        LOGGER.info("admin {} is deleting all meals for restaurant {}", loggedAdminEmail, restaurantName);
        service.deleteAllMeals(loggedAdminEmail, restaurantName);
    }
}
