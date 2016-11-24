package isr.ek0.orderapi.repo.user;

import isr.ek0.orderapi.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CrudUserRepository extends MongoRepository<User, String> {

}
