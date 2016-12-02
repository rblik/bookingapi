package isr.ek0.bookingapi.util.exception;

import isr.ek0.bookingapi.model.Booking;
import isr.ek0.bookingapi.model.Restaurant;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static java.time.LocalDateTime.now;
import static java.util.stream.Collectors.toList;

public class ExceptionUtil {
    public ExceptionUtil() {
    }

    public static Booking validateBooking(Booking booking, Restaurant restaurant) {
        if (booking.getTime() == null || booking.getTime().isAfter(restaurant.getCloseTime())
                || booking.getTime().isBefore(restaurant.getOpenTime())) {
            throw new WrongBookingException("booking for restaurant " + restaurant.getName() + " must be for a time from " + restaurant.getOpenTime() + " to " + restaurant.getCloseTime());
        }
        LocalDate date = booking.getBookingId().getDate();
        if (date == null) {
            throw new WrongBookingException("booking date must not be null");
        }
        LocalDateTime ldt = LocalDateTime.of(booking.getBookingId().getDate(), booking.getTime());
        LocalDateTime earliestDateTime = now().plusHours(2);
        if (ldt.isBefore(earliestDateTime)) {
            throw new WrongBookingException("booking must be for a datetime 2 hours from now minimum");
        }
        return booking;
    }

    public static void checkNotFound(int result, Object id, Class clazz, boolean isModification) {
        if (result == 0 && isModification) {
            checkNotFound(null, id, clazz);
        }
    }

    public static <T, S> T checkNotFound(T object, Object id, Class<S> clazz) {
        if (object == null) {
            throw new NotFoundException("Not found " + clazz.getSimpleName().toLowerCase() + " with id = " + id);
        }
        return object;
    }

    public static List<Double> validateCoordinates(List<Double> coordinates) {
        List<Double> coordinatesValidated = coordinates.stream().filter(coordinate -> checkNotNull(coordinate, new WrongCoordinatesException("wrong coordinate, valid format example - 0.0"))).collect(toList());
        if ((Double.compare(-180.0, coordinatesValidated.get(0)) == 1) ||
                (Double.compare(180.0, coordinatesValidated.get(0)) == -1) ||
                (Double.compare(-90.0, coordinatesValidated.get(1)) == 1) ||
                (Double.compare(90.0, coordinatesValidated.get(1)) == -1)) {
            throw new WrongCoordinatesException("wrong coordinate boundaries, longitude must be in range (-180.0...180.0), latitude (-90.0...90.0)");
        }
        return coordinatesValidated;
    }

    private static boolean checkNotNull(Double coordinate, RuntimeException ex) {
        if (coordinate == null) {
            throw ex;
        }
        return true;
    }

}
