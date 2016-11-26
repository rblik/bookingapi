package isr.ek0.bookingapi.service;

import isr.ek0.bookingapi.model.User;

public interface UserService {
    User get(String email);
}
