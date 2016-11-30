package isr.ek0.bookingapi.testutils;

import isr.ek0.bookingapi.model.Restaurant;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.GeospatialIndex;
import org.springframework.data.mongodb.core.index.Index;

import static isr.ek0.bookingapi.testutils.BookingUtil.BOOKINGS;
import static isr.ek0.bookingapi.testutils.RestaurantUtil.RESTAURANTS;
import static isr.ek0.bookingapi.testutils.UsersUtil.USERS;
import static org.springframework.data.domain.Sort.Direction.ASC;
import static org.springframework.data.mongodb.core.index.GeoSpatialIndexType.GEO_2DSPHERE;

public class TestUtil {

    public static void repopulateDB(MongoTemplate template) {
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
