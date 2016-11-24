package isr.ek0.orderapi.service;

import isr.ek0.orderapi.Application;
import isr.ek0.orderapi.model.Meal;
import isr.ek0.orderapi.model.Restaurant;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.test.context.junit4.SpringRunner;

import static isr.ek0.orderapi.util.UsersUtil.TEST_USERS;
import static java.util.Arrays.asList;

@RunWith(SpringRunner.class)
@SpringApplicationConfiguration({Application.class})
public class RestaurantServiceTest {

    @Autowired
    private RestaurantService service;

    @Autowired
    private MongoTemplate template;

    @Before
    public void init() {
        template.dropCollection("users");
        template.dropCollection("restaurants");
        template.insertAll(TEST_USERS);
    }

    @Test
    public void testSaveRestaurant() {
        Restaurant restaurant = new Restaurant("My Restaurant", new GeoJsonPoint(10, -40),
                asList(new Meal("Porridge", 30, 10), new Meal("Soup", 45, 15)));
        service.save(restaurant);
    }
}