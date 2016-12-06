package isr.ek0.bookingapi.repo;

import isr.ek0.bookingapi.model.Booking;

import java.time.LocalDate;
import java.util.List;

public interface BookingRepository {

    Booking save(String loggedUserEmail, Booking booking);

    List<Booking> getAll(String loggedUserEmail);

    List<Booking> getAllByRestaurantName(String restaurantName, LocalDate today);

    void deleteAllByRestaurant(String restaurantName);

    void delete(String loggedUserEmail, LocalDate date);

    void deleteAll(String loggedUserEmail);
}
