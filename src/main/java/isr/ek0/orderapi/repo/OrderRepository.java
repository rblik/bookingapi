package isr.ek0.orderapi.repo;

import isr.ek0.orderapi.model.Order;

import java.time.LocalDate;
import java.util.List;

public interface OrderRepository {

    void save(String loggedUserEmail, Order order);

    List<Order> getAll(String loggedUserEmail);

    List<Order> getAllByRestaurantName(String loggedUserEmail, String restaurantName, LocalDate today);
}
