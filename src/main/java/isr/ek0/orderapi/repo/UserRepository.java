package isr.ek0.orderapi.repo;

import isr.ek0.orderapi.model.User;

public interface UserRepository {

    User get(String email);
}
