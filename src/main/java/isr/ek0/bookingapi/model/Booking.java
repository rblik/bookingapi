package isr.ek0.bookingapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.hateoas.ResourceSupport;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;

@Document(collection = "bookings")
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "bookingId", callSuper = false)
public class Booking extends ResourceSupport implements Serializable {
    @Id
    @JsonUnwrapped
    private BookingId bookingId;
    private LocalTime time;
    @JsonProperty(access = READ_ONLY)
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
