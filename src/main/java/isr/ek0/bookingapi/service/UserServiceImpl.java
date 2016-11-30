package isr.ek0.bookingapi.service;

import isr.ek0.bookingapi.model.User;
import isr.ek0.bookingapi.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static isr.ek0.bookingapi.util.exception.ExceptionUtil.checkNotFound;
import static org.springframework.util.Assert.notNull;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User get(String email) {
        notNull(email, "email must not be null");
        return checkNotFound(userRepository.get(email), email, User.class);
    }

    @Override
    public User save(User user) {
        notNull(user, "user must not be null");
        return userRepository.save(user);
    }

    @Override
    public List<User> getAll() {
        return userRepository.getAll();
    }
}
