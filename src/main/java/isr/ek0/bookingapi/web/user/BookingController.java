package isr.ek0.bookingapi.web.user;

import isr.ek0.bookingapi.model.Booking;
import isr.ek0.bookingapi.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import static isr.ek0.bookingapi.AuthorizedUser.logged_user_email;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingService service;

    @GetMapping
    public List<Booking> getAll() {
        return service.getAll(logged_user_email);
    }

    @PostMapping("/{restaurantName}")
    public ResponseEntity<Booking> save(@Valid @RequestBody Booking booking, @PathVariable String restaurantName) {
        booking.setRestaurantName(restaurantName);
        Booking bookingSaved = service.save(logged_user_email, booking);
        URI uriOfBookings = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/bookings").build().toUri();
        return ResponseEntity.created(uriOfBookings).body(bookingSaved);
    }

    @DeleteMapping
    public void delete(@RequestParam LocalDate date) {
        service.delete(logged_user_email, date);
    }
}
