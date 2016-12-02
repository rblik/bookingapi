package isr.ek0.bookingapi.service;

import com.google.common.annotations.VisibleForTesting;
import isr.ek0.bookingapi.model.User;

import java.util.List;

public interface UserService {
    User get(String email);

    User save(User user);

    @VisibleForTesting
    List<User> getAll();

    void delete(String logged_user_email);
}
