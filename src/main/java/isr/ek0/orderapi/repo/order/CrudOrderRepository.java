package isr.ek0.orderapi.repo.order;

import isr.ek0.orderapi.model.Order;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface CrudOrderRepository extends MongoRepository<Order, ObjectId> {

    @Query("{'userEmail':'?0'}")
    List<Order> findAllByUserEmail(String userEmail);
}
