package isr.ek0.orderapi.repo;

import isr.ek0.orderapi.Application;
import isr.ek0.orderapi.model.User;
import isr.ek0.orderapi.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static isr.ek0.orderapi.util.UsersUtil.ADMIN;
import static isr.ek0.orderapi.util.UsersUtil.TEST_USERS;
import static isr.ek0.orderapi.util.UsersUtil.USER_1;
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
        template.dropCollection(User.class);
        template.insertAll(TEST_USERS);
    }

    @Test
    public void testGet() {
        User userFound = service.get(ADMIN.getEmail());
        assertEquals(ADMIN, userFound);
        assertNotEquals(USER_1, userFound);
    }
}