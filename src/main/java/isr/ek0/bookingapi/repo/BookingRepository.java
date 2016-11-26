package isr.ek0.bookingapi.repo;

import isr.ek0.bookingapi.model.Booking;

import java.time.LocalDate;
import java.util.List;

public interface BookingRepository {

    void save(String loggedUserEmail, Booking booking);

    List<Booking> getAll(String loggedUserEmail);

    List<Booking> getAllByRestaurantName(String loggedUserEmail, String restaurantName, LocalDate today);
}
