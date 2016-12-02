package isr.ek0.bookingapi.web.user;

import isr.ek0.bookingapi.model.Booking;
import isr.ek0.bookingapi.service.BookingService;
import isr.ek0.bookingapi.web.webutil.LocalDateFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

import static isr.ek0.bookingapi.AuthorizedUser.logged_user_email;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingService service;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addCustomFormatter(new LocalDateFormatter());
    }

    @GetMapping
    public List<Booking> getAll() {
        return service.getAll(logged_user_email);
    }

    @PostMapping("/{restaurantName}")
    public Booking save(@Valid @RequestBody Booking booking, @PathVariable String restaurantName) {
        booking.setRestaurantName(restaurantName);
        return service.save(logged_user_email, booking);
    }

    @DeleteMapping
    public void delete(@RequestParam LocalDate date) {
        service.delete(logged_user_email, date);
    }
}
