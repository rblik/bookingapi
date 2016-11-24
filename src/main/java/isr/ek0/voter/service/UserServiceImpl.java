package isr.ek0.voter.service;

import isr.ek0.voter.model.User;
import isr.ek0.voter.repo.CrudUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private CrudUserRepository userRepository;

    @Override
    public User get(String email) {
        //// TODO: 24.11.2016 @NotNull and other checking 
        return userRepository.findOne(email);
    }
}
