package isr.ek0.bookingapi.web;

import isr.ek0.bookingapi.model.Booking;
import isr.ek0.bookingapi.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static isr.ek0.bookingapi.AuthorizedUser.logged_admin_email;
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

    @GetMapping("/{restaurantName}/admin")
    public List<Booking> getAllByRestaurantName(@PathVariable String restaurantName) {
        return service.getAllByRestaurantName(logged_admin_email, restaurantName);
    }

    @PostMapping("/{restaurantName}")
    public Booking save(@RequestBody Booking booking) {
        return service.save(logged_user_email, booking);
    }
}
