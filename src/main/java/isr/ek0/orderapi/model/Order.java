package isr.ek0.orderapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private LocalDateTime dateTime;
    private String userEmail;
    private String restaurantName;

    public Order(LocalDateTime dateTime, String restaurantName) {
        this.dateTime = dateTime;
        this.restaurantName = restaurantName;
    }
}
