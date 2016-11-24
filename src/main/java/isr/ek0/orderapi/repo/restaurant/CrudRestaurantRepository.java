package isr.ek0.orderapi.repo.restaurant;

import isr.ek0.orderapi.model.Restaurant;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CrudRestaurantRepository extends MongoRepository<Restaurant, String>{

}
