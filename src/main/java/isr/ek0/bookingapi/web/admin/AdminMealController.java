package isr.ek0.bookingapi.web.admin;

import isr.ek0.bookingapi.model.Meal;
import isr.ek0.bookingapi.model.Restaurant;
import isr.ek0.bookingapi.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;

import java.net.URI;

import static isr.ek0.bookingapi.AuthorizedUser.logged_admin_email;

@RestController
@RequestMapping("/admin/restaurants/{restaurantName}/meals")
public class AdminMealController {

    @Autowired
    private RestaurantService service;


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> saveMeal(@Valid @RequestBody Meal meal, @PathVariable String restaurantName) {
        Restaurant restaurant = service.saveMeal(logged_admin_email, restaurantName, meal);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/admin/restaurants/{restaurantName}")
                .buildAndExpand(restaurant.getName()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(restaurant);
    }

    @DeleteMapping(params = {"description"})
    public void deleteMeal(@PathVariable String restaurantName, @RequestParam String description) {
        service.deleteMeal(logged_admin_email, restaurantName, description);
    }

    @DeleteMapping
    public void deleteMeals(@PathVariable String restaurantName) {
        service.deleteAllMeals(logged_admin_email, restaurantName);
    }
}
