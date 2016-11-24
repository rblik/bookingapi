package isr.ek0.orderapi.service;

import isr.ek0.orderapi.model.Restaurant;
import isr.ek0.orderapi.repo.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Override
    public void save(Restaurant restaurant) {
        restaurantRepository.save(restaurant);
    }
}
