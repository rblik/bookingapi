package isr.ek0.bookingapi.repo.user;

import isr.ek0.bookingapi.model.User;
import isr.ek0.bookingapi.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository{
    @Autowired
    private MongoTemplate template;
    @Autowired
    private CrudUserRepository crudRepository;

    @Override
    public User get(String email) {
        return crudRepository.findOne(email);
    }

    @Override
    public User save(User user) {
        return crudRepository.insert(user);
    }

    @Override
    public List<User> getAll() {
        return crudRepository.findAll();
    }

    @Override
    public void delete(String loggedUserEmail) {
        crudRepository.delete(loggedUserEmail);
    }
}
