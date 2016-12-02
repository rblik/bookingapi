package isr.ek0.bookingapi.repo;

import com.google.common.annotations.VisibleForTesting;
import isr.ek0.bookingapi.model.User;

import java.util.List;

public interface UserRepository {

    User get(String email);

    User save(User user);

    @VisibleForTesting
    List<User> getAll();

    void delete(String loggedUserEmail);
}
