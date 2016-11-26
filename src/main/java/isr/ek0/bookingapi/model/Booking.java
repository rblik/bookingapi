package isr.ek0.bookingapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalTime;

@Document(collection = "bookings")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Booking {
    @Id
    private BookingId bookingId;
    private LocalTime time;
    private String restaurantName;

    public Booking(LocalDate date, LocalTime time, String restaurantName) {
        this.bookingId = new BookingId(null, date);
        this.time = time;
        this.restaurantName = restaurantName;
    }
}
