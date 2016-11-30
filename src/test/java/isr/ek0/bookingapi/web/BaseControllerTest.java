package isr.ek0.bookingapi.web;

import isr.ek0.bookingapi.service.RestaurantService;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static isr.ek0.bookingapi.testutils.TestUtil.repopulateDB;

@SuppressWarnings("SpringJavaAutowiredMembersInspection")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class BaseControllerTest {

    protected static final Logger LOGGER = LoggerFactory.getLogger(BaseControllerTest.class);
    @Autowired
    protected MongoTemplate mongoTemplate;
    @Autowired
    protected RestaurantService restaurantService;
    @Autowired
    protected TestRestTemplate restTemplate;

    @Before
    public void init() {
        restaurantService.evictCache();
        repopulateDB(mongoTemplate);
    }
}
