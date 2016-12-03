package isr.ek0.bookingapi.web.user;

import isr.ek0.bookingapi.AuthorizedUser;
import isr.ek0.bookingapi.model.Booking;
import isr.ek0.bookingapi.service.BookingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;

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
        return service.getAll(loggedUserEmail);
    }

    @PostMapping("/{restaurantName}")
    public ResponseEntity<Booking> save(@Valid @RequestBody Booking booking, @PathVariable String restaurantName) {
        String loggedUserEmail = AuthorizedUser.mail();
        booking.setRestaurantName(restaurantName);
        LOGGER.info("{} is saving {} for {}", loggedUserEmail, booking, restaurantName);
        Booking bookingSaved = service.save(loggedUserEmail, booking);
        URI uriOfBookings = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/bookings").build().toUri();
        return ResponseEntity.created(uriOfBookings).body(bookingSaved);
    }

    @DeleteMapping
    public void delete(@RequestParam LocalDate date) {
        String loggedUserEmail = AuthorizedUser.mail();
        LOGGER.info("{} is deleting his booking for {}", date);
        service.delete(loggedUserEmail, date);
    }
}
