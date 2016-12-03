package isr.ek0.bookingapi.web.admin;

import isr.ek0.bookingapi.AuthorizedUser;
import isr.ek0.bookingapi.model.Booking;
import isr.ek0.bookingapi.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/admin/restaurants/{restaurantName}/bookings")
public class AdminBookingController {

    @Autowired
    private BookingService service;

    @GetMapping
    public List<Booking> getAllBookingsByRestaurant(@PathVariable String restaurantName, @RequestParam(required = false) LocalDate date) {
        return service.getAllByRestaurantName(AuthorizedUser.mail(), restaurantName, date);
    }
}
