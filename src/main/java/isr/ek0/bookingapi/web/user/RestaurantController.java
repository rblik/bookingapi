package isr.ek0.bookingapi.web.user;

import isr.ek0.bookingapi.model.Restaurant;
import isr.ek0.bookingapi.service.RestaurantService;
import isr.ek0.bookingapi.to.RestaurantWithDistance;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@Slf4j
@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantService service;

    @GetMapping
    public List<Restaurant> getAll(@RequestParam(required = false, value = "order") String order) {
        log.info("retrieving all restaurants");
        List<Restaurant> all = service.getAll(order);
        all.forEach(restaurant -> {
            restaurant.removeLinks();
            restaurant.add(linkTo(RestaurantController.class)
                    .slash(restaurant.getName()).withSelfRel());
        });
        return all;
    }

    @GetMapping("/{restaurantName}")
    public Restaurant get(@PathVariable String restaurantName) {
        log.info("retrieving restaurant {}", restaurantName);
        Restaurant restaurant = service.get(restaurantName);
        restaurant.removeLinks();
        restaurant.add(linkTo(RestaurantController.class).withRel("getAll"));
        return restaurant;
    }

    @GetMapping(params = {"longitude", "latitude"})
    public List<RestaurantWithDistance> getByGeo(@RequestParam String longitude,
                                                 @RequestParam String latitude,
                                                 @RequestParam(required = false) String distance) {
        log.info("retrieving restaurants for coordinates: {}, {}", longitude, latitude);
        List<RestaurantWithDistance> allByLocationAndDistance = service.getAllByLocationAndDistance(longitude, latitude, distance);
        allByLocationAndDistance.forEach((restaurant -> {
            restaurant.removeLinks();
            restaurant.add(linkTo(RestaurantController.class).
                    slash(restaurant.getName()).withSelfRel());
        }));
        return allByLocationAndDistance;
    }
}
