package isr.ek0.bookingapi.service;

import isr.ek0.bookingapi.Application;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static isr.ek0.bookingapi.testutils.PopulateUtil.repopulateDB;

@RunWith(SpringRunner.class)
@SpringApplicationConfiguration({Application.class})
public abstract class BaseServiceTest {

    protected static final Logger LOGGER = LoggerFactory.getLogger(BaseServiceTest.class);
    @Autowired
    protected MongoTemplate mongoTemplate;
    @Autowired
    protected UserService userService;
    @Autowired
    protected RestaurantService restaurantService;
    @Autowired
    protected BookingService bookingService;

    @Before
    public void init() {
        restaurantService.evictCache();
        repopulateDB(mongoTemplate);
    }
}
