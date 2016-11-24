package isr.ek0.voter.service;

import isr.ek0.voter.model.User;

public interface UserService {
    User get(String email);
}
