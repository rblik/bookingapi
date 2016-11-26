package isr.ek0.orderapi.service;

import isr.ek0.orderapi.model.Order;

import java.util.List;

public interface OrderService {

    void save(String loggedUserEmail, Order order);

    List<Order> getAll(String loggedUserEmail);

    List<Order> getAllByRestaurantName(String loggedUserEmail, String restaurantName);
}
