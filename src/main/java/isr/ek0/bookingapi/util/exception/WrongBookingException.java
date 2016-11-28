package isr.ek0.bookingapi.util.exception;

public class WrongBookingException extends RuntimeException {
    public WrongBookingException(String message) {
        super(message);
    }
}
