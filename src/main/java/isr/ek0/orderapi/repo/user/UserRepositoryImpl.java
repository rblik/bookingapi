package isr.ek0.orderapi.repo.user;

import isr.ek0.orderapi.model.Restaurant;
import isr.ek0.orderapi.model.User;
import isr.ek0.orderapi.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Repository
public class UserRepositoryImpl implements UserRepository{
    @Autowired
    private MongoTemplate template;
    @Autowired
    private CrudUserRepository repository;

    @Override
    public void addRestaurant(String loggedUserEmail, Restaurant restaurant) {
        template.updateFirst(new Query(where("email").is(loggedUserEmail)),
                new Update().addToSet("restaurants", restaurant.getName()), User.class);
    }

    @Override
    public User get(String email) {
        return repository.findOne(email);
    }
}
