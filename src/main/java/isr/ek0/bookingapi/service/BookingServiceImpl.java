package isr.ek0.bookingapi.service;

import isr.ek0.bookingapi.model.Booking;
import isr.ek0.bookingapi.repo.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.time.LocalDate.now;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Override
    public void save(String loggedUserEmail, Booking booking) {
        bookingRepository.save(loggedUserEmail, booking);
    }

    @Override
    public List<Booking> getAll(String loggedUserEmail) {
        return bookingRepository.getAll(loggedUserEmail);
    }

    @Override
    public List<Booking> getAllByRestaurantName(String loggedUserEmail, String restaurantName) {
        return bookingRepository.getAllByRestaurantName(loggedUserEmail, restaurantName, now());
    }
}
