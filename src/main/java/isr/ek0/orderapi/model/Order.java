package isr.ek0.orderapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalTime;

@Document(collection = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    private OrderId orderId;
    private LocalTime time;
    private String restaurantName;

    public Order(LocalDate date, LocalTime time, String restaurantName) {
        this.orderId = new OrderId(null, date);
        this.time = time;
        this.restaurantName = restaurantName;
    }
}
