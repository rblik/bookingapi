package isr.ek0.bookingapi.util.exception;

public class ExceptionUtil {
    public ExceptionUtil() {
    }

    public static <T, S> T checkNotFound(T object, Object id, Class<S> clazz) {
        if (object == null) {
            throw new NotFoundException("Not found " + clazz.toString().toLowerCase() + " with id = " + id);
        }
        return object;
    }
}
