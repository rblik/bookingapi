package isr.ek0.bookingapi.service;

import isr.ek0.bookingapi.model.Booking;

import java.time.LocalDate;
import java.util.List;

public interface BookingService {

    Booking save(String loggedUserEmail, Booking booking);

    List<Booking> getAll(String loggedUserEmail);

    List<Booking> getAllByRestaurantName(String loggedUserEmail, String restaurantName, LocalDate date);

    void delete(String loggedUserEmail, LocalDate date);

    void deleteAll(String loggedUserEmail);

    void deleteAllByRestaurant(String restaurantName);
}
