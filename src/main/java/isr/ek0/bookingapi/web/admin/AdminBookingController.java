package isr.ek0.bookingapi.web.admin;

import isr.ek0.bookingapi.model.Booking;
import isr.ek0.bookingapi.service.BookingService;
import isr.ek0.bookingapi.web.webutil.LocalDateFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import static isr.ek0.bookingapi.AuthorizedUser.logged_admin_email;

@RestController
@RequestMapping("/admin/restaurants/{restaurantName}/bookings")
public class AdminBookingController {

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addCustomFormatter(new LocalDateFormatter());
    }

    @Autowired
    private BookingService service;

    @GetMapping
    public List<Booking> getAllBookingsByRestaurant(@PathVariable String restaurantName, @RequestParam(required = false) LocalDate date) {
        return service.getAllByRestaurantName(logged_admin_email, restaurantName, date);
    }
}
