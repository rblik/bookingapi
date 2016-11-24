package isr.ek0.orderapi.service;

import isr.ek0.orderapi.Application;
import isr.ek0.orderapi.model.Meal;
import isr.ek0.orderapi.model.Restaurant;
import isr.ek0.orderapi.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.test.context.junit4.SpringRunner;

import static isr.ek0.orderapi.util.UsersUtil.*;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@RunWith(SpringRunner.class)
@SpringApplicationConfiguration({Application.class})
public class UserServiceTest {

    @Autowired
    UserService service;
    @Autowired
    MongoTemplate template;

    @Before
    public void init() {
        template.dropCollection("users");
        template.dropCollection("restaurants");
        template.insertAll(TEST_USERS);
    }

    @Test
    public void testGet() {
        User userFound = service.get(ADMIN.getEmail());
        assertEquals(ADMIN, userFound);
        assertNotEquals(USER_1, userFound);
    }

    @Test
    public void testSaveRestaurant() {
        Restaurant restaurant = new Restaurant("My Restaurant", new GeoJsonPoint(10, -40), asList(new Meal("Porridge", 30, 10)));
        service.addRestaurant(ADMIN.getEmail(), restaurant);
    }
}