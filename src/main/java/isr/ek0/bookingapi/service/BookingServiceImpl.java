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

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Override
    public void save(@NonNull String loggedUserEmail, Booking booking) {
        bookingRepository.save(loggedUserEmail, validateBooking(booking));
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
