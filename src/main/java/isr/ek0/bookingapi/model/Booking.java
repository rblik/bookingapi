package isr.ek0.bookingapi.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Document(collection = "bookings")
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "bookingId")
public class Booking implements Serializable {
    @Id
    private BookingId bookingId;
    private LocalTime time;
    private String restaurantName;

    public Booking(LocalDate date, LocalTime time, String restaurantName) {
        this(null, date, time, restaurantName);
    }

    public Booking(String userEmail, LocalDate date, LocalTime time, String restaurantName) {
        this.bookingId = new BookingId(userEmail, date);
        this.time = time;
        this.restaurantName = restaurantName;
    }
}
