package isr.ek0.bookingapi.service;

import isr.ek0.bookingapi.model.Booking;
import isr.ek0.bookingapi.model.Restaurant;
import isr.ek0.bookingapi.repo.BookingRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static isr.ek0.bookingapi.util.exception.ExceptionUtil.checkNotFound;
import static isr.ek0.bookingapi.util.exception.ExceptionUtil.validateBooking;
import static java.time.LocalDate.now;
import static org.springframework.util.Assert.notNull;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private RestaurantService restaurantService;

    @Override
    public Booking save(@NonNull String loggedUserEmail, Booking booking) {
        notNull(booking, "booking must not be null");
        Restaurant restaurant = restaurantService.get(booking.getRestaurantName());
        return checkNotFound(bookingRepository.save(loggedUserEmail, validateBooking(booking, restaurant)), booking.getRestaurantName(), Restaurant.class);
    }

    @Override
    public List<Booking> getAll(@NonNull String loggedUserEmail) {
        return bookingRepository.getAll(loggedUserEmail);
    }

    @Override
    public List<Booking> getAllByRestaurantName(@NonNull String loggedUserEmail, String restaurantName) {
        return checkNotFound(bookingRepository.getAllByRestaurantName(loggedUserEmail, restaurantName, now()), restaurantName, Restaurant.class);
    }
}
