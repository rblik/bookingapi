package isr.ek0.orderapi.repo;

import isr.ek0.orderapi.model.Restaurant;
import isr.ek0.orderapi.model.User;

public interface UserRepository {

    void addRestaurant(String loggedUserEmail, Restaurant restaurant);

    User get(String email);
}
