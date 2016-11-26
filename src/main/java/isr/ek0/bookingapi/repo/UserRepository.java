package isr.ek0.bookingapi.repo;

import isr.ek0.bookingapi.model.User;

public interface UserRepository {

    User get(String email);
}
