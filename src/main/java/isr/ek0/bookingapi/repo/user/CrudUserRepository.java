package isr.ek0.bookingapi.repo.user;

import isr.ek0.bookingapi.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CrudUserRepository extends MongoRepository<User, String> {

}
