package isr.ek0.orderapi.repo.restaurant;

import isr.ek0.orderapi.model.Restaurant;
import isr.ek0.orderapi.repo.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RestaurantRepositoryImpl implements RestaurantRepository{

    @Autowired
    private CrudRestaurantRepository crudRepository;

    @Autowired
    private MongoTemplate template;

    public void save(Restaurant restaurant) {
        template.insert(restaurant);
    }
}
