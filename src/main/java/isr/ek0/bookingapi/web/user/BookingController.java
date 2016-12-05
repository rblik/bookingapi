package isr.ek0.bookingapi.web.user;

import isr.ek0.bookingapi.AuthorizedUser;
import isr.ek0.bookingapi.model.Booking;
import isr.ek0.bookingapi.service.BookingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookingController.class);

    @Autowired
    private BookingService service;

    @GetMapping
    public List<Booking> getAll() {
        String loggedUserEmail = AuthorizedUser.mail();
        LOGGER.info("{} is retrieving all his bookings", loggedUserEmail);
        List<Booking> all = service.getAll(loggedUserEmail);
        all.forEach(booking -> booking.add(linkTo(RestaurantController.class).slash(booking.getRestaurantName()).withRel(booking.getRestaurantName())));
        return all;
    }

    @PostMapping("/{restaurantName}")
    public ResponseEntity<Booking> save(@Valid @RequestBody Booking booking, @PathVariable String restaurantName) {
        String loggedUserEmail = AuthorizedUser.mail();
        booking.setRestaurantName(restaurantName);
        LOGGER.info("{} is saving {} for {}", loggedUserEmail, booking, restaurantName);
        Booking bookingSaved = service.save(loggedUserEmail, booking);
        bookingSaved.add(linkTo(BookingController.class).withRel("bookings"));
        bookingSaved.add(linkTo(RestaurantController.class).slash(booking.getRestaurantName()).withRel(booking.getRestaurantName()));
        return ResponseEntity.status(CREATED).body(bookingSaved);
    }

    @DeleteMapping
    public void delete(@RequestParam LocalDate date) {
        String loggedUserEmail = AuthorizedUser.mail();
        LOGGER.info("{} is deleting his booking for {}", loggedUserEmail,date);
        service.delete(loggedUserEmail, date);
    }
}
