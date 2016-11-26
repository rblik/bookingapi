package isr.ek0.orderapi.repo.user;

import isr.ek0.orderapi.model.User;
import isr.ek0.orderapi.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository{
    @Autowired
    private MongoTemplate template;
    @Autowired
    private CrudUserRepository repository;

    @Override
    public User get(String email) {
        return repository.findOne(email);
    }
}
