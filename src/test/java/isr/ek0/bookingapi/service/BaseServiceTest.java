package isr.ek0.bookingapi.service;

import isr.ek0.bookingapi.Application;
import isr.ek0.bookingapi.model.Restaurant;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.GeospatialIndex;
import org.springframework.data.mongodb.core.index.Index;
import org.springframework.test.context.junit4.SpringRunner;

import static isr.ek0.bookingapi.testutils.BookingUtil.BOOKINGS;
import static isr.ek0.bookingapi.testutils.RestaurantUtil.RESTAURANTS;
import static isr.ek0.bookingapi.testutils.UsersUtil.USERS;
import static org.springframework.data.domain.Sort.Direction.ASC;
import static org.springframework.data.mongodb.core.index.GeoSpatialIndexType.GEO_2DSPHERE;

@RunWith(SpringRunner.class)
@SpringApplicationConfiguration({Application.class})
public abstract class BaseServiceTest {

    protected static final Logger LOGGER = LoggerFactory.getLogger(BaseServiceTest.class);
    @Autowired
    protected MongoTemplate template;
    @Autowired
    protected UserService userService;
    @Autowired
    protected RestaurantService restaurantService;
    @Autowired
    protected BookingService bookingService;

    @Before
    public void init() {
        restaurantService.evictCache();
        template.dropCollection("users");
        template.dropCollection("restaurants");
        template.dropCollection("bookings");
        template.insertAll(RESTAURANTS);
        template.insertAll(USERS);
        template.insertAll(BOOKINGS);
//        recreate index after drop
        template.indexOps(Restaurant.class).ensureIndex(new GeospatialIndex("location").typed(GEO_2DSPHERE));
        template.indexOps(Restaurant.class).ensureIndex(new Index("ownerEmail", ASC));
    }
}
