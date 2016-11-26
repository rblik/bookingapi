package isr.ek0.orderapi.service;

import isr.ek0.orderapi.model.Order;
import isr.ek0.orderapi.repo.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public void save(String loggedUserEmail, Order order) {
        orderRepository.save(loggedUserEmail, order);
    }

    @Override
    public List<Order> getAll(String loggedUserEmail) {
        return orderRepository.getAll(loggedUserEmail);
    }

    @Override
    public List<Order> getAllByRestaurantName(String loggedUserEmail, String restaurantName) {
        return orderRepository.getAllByRestaurantName(loggedUserEmail, restaurantName);
    }
}
