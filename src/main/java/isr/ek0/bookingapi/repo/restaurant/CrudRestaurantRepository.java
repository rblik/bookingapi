package isr.ek0.bookingapi.repo.restaurant;

import isr.ek0.bookingapi.model.Restaurant;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface CrudRestaurantRepository extends MongoRepository<Restaurant, String>{

    @Query("{'ownerEmail':'?0'}")
    List<Restaurant> findByOwnerEmail(String ownerEmail);
}
