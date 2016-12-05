package isr.ek0.bookingapi.service;

import isr.ek0.bookingapi.AuthorizedUser;
import isr.ek0.bookingapi.model.User;
import isr.ek0.bookingapi.repo.UserRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

import static isr.ek0.bookingapi.util.encoding.PasswordUtil.prepareToSave;
import static isr.ek0.bookingapi.util.exception.ExceptionUtil.checkNotFound;
import static org.springframework.util.Assert.notNull;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User get(@NonNull String loggedUserEmail) {
        notNull(loggedUserEmail, "email must not be null");
        return checkNotFound(userRepository.get(loggedUserEmail), loggedUserEmail, User.class);
    }

    @Override
    public User save(User user) {
        notNull(user, "user must not be null");
        return userRepository.save(prepareToSave(user));
    }

    @Override
    public List<User> getAll() {
        return userRepository.getAll();
    }

    @Override
    public void delete(String loggedUserEmail) {
        userRepository.delete(loggedUserEmail);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User u = userRepository.get(email.toLowerCase());
        if (u == null) {
            throw new UsernameNotFoundException("User " + email + " is not found");
        }
        return new AuthorizedUser(u);
    }
}
