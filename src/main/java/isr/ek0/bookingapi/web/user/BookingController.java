package isr.ek0.bookingapi.web.user;

import isr.ek0.bookingapi.AuthorizedUser;
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

@RestController
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingService service;

    @GetMapping
    public List<Booking> getAll() {
        return service.getAll(AuthorizedUser.mail());
    }

    @PostMapping("/{restaurantName}")
    public ResponseEntity<Booking> save(@Valid @RequestBody Booking booking, @PathVariable String restaurantName) {
        booking.setRestaurantName(restaurantName);
        Booking bookingSaved = service.save(AuthorizedUser.mail(), booking);
        URI uriOfBookings = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/bookings").build().toUri();
        return ResponseEntity.created(uriOfBookings).body(bookingSaved);
    }

    @DeleteMapping
    public void delete(@RequestParam LocalDate date) {
        service.delete(AuthorizedUser.mail(), date);
    }
}
