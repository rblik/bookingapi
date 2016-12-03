package isr.ek0.bookingapi.web.user;

import isr.ek0.bookingapi.model.Restaurant;
import isr.ek0.bookingapi.service.RestaurantService;
import isr.ek0.bookingapi.to.RestaurantWithDistance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.google.common.collect.ImmutableList.of;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestaurantController.class);

    @Autowired
    private RestaurantService service;

    @GetMapping
    public List<Restaurant> getAll(@RequestParam(required = false, value = "order") String order) {
        LOGGER.info("retrieving all restaurants");
        return service.getAll(order);
    }

    @GetMapping("/{restaurantName}")
    public Restaurant get(@PathVariable String restaurantName) {
        LOGGER.info("retrieving restaurant {}", restaurantName);
        return service.get(restaurantName);
    }

    @GetMapping(params = {"longitude", "latitude"})
    public List<RestaurantWithDistance> getByGeo(@RequestParam String longitude,
                                                                @RequestParam String latitude,
                                                                @RequestParam(required = false) String distance) {
        LOGGER.info("retrieving restaurants for coordinates: {}, {}", longitude, latitude);
        return service.getAllByLocationAndDistance(of(longitude, latitude), distance);
    }
}
