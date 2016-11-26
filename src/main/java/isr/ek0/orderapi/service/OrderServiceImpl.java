package isr.ek0.orderapi.service;

import isr.ek0.orderapi.model.Order;
import isr.ek0.orderapi.repo.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static java.time.LocalDateTime.now;
import static java.time.temporal.ChronoUnit.DAYS;

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
        LocalDateTime today = now().truncatedTo(DAYS);
        LocalDateTime tomorrow = today.plusDays(1);
        return orderRepository.getAllByRestaurantName(loggedUserEmail, restaurantName, today, tomorrow);
    }
}
