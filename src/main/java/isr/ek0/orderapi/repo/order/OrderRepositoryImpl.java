package isr.ek0.orderapi.repo.order;

import isr.ek0.orderapi.model.Order;
import isr.ek0.orderapi.model.Restaurant;
import isr.ek0.orderapi.repo.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Repository
public class OrderRepositoryImpl implements OrderRepository {

    @Autowired
    MongoTemplate template;

    @Autowired
    private CrudOrderRepository crudRepository;

    @Override
    public void save(String loggedUserEmail, Order order) {
        order.setUserEmail(loggedUserEmail);
        template.save(order, "orders");
    }

    @Override
    public List<Order> getAll(String loggedUserEmail) {
        return crudRepository.findAllByUserEmail(loggedUserEmail);
    }

    @Override
    public List<Order> getAllByRestaurantName(String loggedUserEmail, String restaurantName, LocalDateTime from, LocalDateTime to) {
        Restaurant restaurant = template.findOne(new Query(where("ownerEmail").is(loggedUserEmail)
                .andOperator(where("_id").is(restaurantName))), Restaurant.class);
        return restaurant == null ? null : template.find(new Query(where("restaurantName").is(restaurantName)
                .andOperator(where("dateTime").gte(from), where("dateTime").lt(to))), Order.class);
    }
}
