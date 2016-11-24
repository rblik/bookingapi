package isr.ek0.voter.repo;

import isr.ek0.voter.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CrudUserRepository extends MongoRepository<User, String>{

}
