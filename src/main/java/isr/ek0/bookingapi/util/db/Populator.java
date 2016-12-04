package isr.ek0.bookingapi.util.db;

import isr.ek0.bookingapi.model.Booking;
import isr.ek0.bookingapi.model.Meal;
import isr.ek0.bookingapi.model.Restaurant;
import isr.ek0.bookingapi.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.index.GeospatialIndex;
import org.springframework.data.mongodb.core.index.Index;

import java.time.LocalDate;
import java.time.LocalTime;

import static com.google.common.collect.ImmutableList.of;
import static isr.ek0.bookingapi.model.Role.ROLE_ADMIN;
import static isr.ek0.bookingapi.model.Role.ROLE_USER;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.springframework.data.domain.Sort.Direction.ASC;
import static org.springframework.data.mongodb.core.index.GeoSpatialIndexType.GEO_2DSPHERE;

public class Populator {
    private static final Logger LOGGER = LoggerFactory.getLogger(Populator.class);

    public static void populateDB(MongoTemplate template) {
        LOGGER.info("begin DB population...");
        template.dropCollection("users");
        template.dropCollection("restaurants");
        template.dropCollection("bookings");
        template.insertAll(of(
                new Restaurant("the_table", new GeoJsonPoint(-40.0, -35.0), of(
                        new Meal("Sushi", 30, 5.0),
                        new Meal("Borsch", 40, 7.0),
                        new Meal("Steak", 35, 8.0)),
                        "admin@gmail.com", LocalTime.of(8, 0), LocalTime.of(23, 0)),
                new Restaurant("aizle", new GeoJsonPoint(-41.0, -32.0), of(
                        new Meal("Meshed Potatoes", 20, 4.0),
                        new Meal("Soup", 30, 5.0),
                        new Meal("Caesar", 35, 5.0)),
                        "admin1@gmail.com", LocalTime.of(7, 0), LocalTime.of(22, 0)),
                new Restaurant("oink", new GeoJsonPoint(-50.0, -33.0), of(
                        new Meal("Pasta", 20, 5.0),
                        new Meal("Soup", 40, 6.0),
                        new Meal("Burger", 20, 6.0)),
                        "admin@gmail.com", LocalTime.of(10, 0), LocalTime.of(23, 0)),
                new Restaurant("pickles", new GeoJsonPoint(-30.0, -38.0), of(
                        new Meal("Kebab", 20, 5.0),
                        new Meal("Falafel", 40, 6.0),
                        new Meal("Djahnun", 20, 6.0)),
                        "admin1@gmail.com", LocalTime.of(9, 0), LocalTime.of(23, 0))));
        template.insertAll(of(
                new User("admin@gmail.com", "Admin", "$2a$10$WejOLxVuXRpOgr4IlzQJ.eT4UcukNqHlAiOVZj1P/nmc8WbpMkiju", asList(ROLE_USER, ROLE_ADMIN)),
                new User("admin1@gmail.com", "Admin1", "$2a$10$WejOLxVuXRpOgr4IlzQJ.eT4UcukNqHlAiOVZj1P/nmc8WbpMkiju", asList(ROLE_USER, ROLE_ADMIN)),
                new User("user@yandex.ru", "User1", "$2a$10$Sh0ZD2NFrzRRJJEKEWn8l.92ROEuzlVyzB9SV1AM8fdluPR0aC1ni", singletonList(ROLE_USER)),
                new User("user@fun.com", "User2", "$2a$10$Sh0ZD2NFrzRRJJEKEWn8l.92ROEuzlVyzB9SV1AM8fdluPR0aC1ni", singletonList(ROLE_USER))));
        template.insertAll(of(
                new Booking("user@yandex.ru", LocalDate.of(2016, 12, 8), LocalTime.of(18, 0), "the_table"),
                new Booking("user@fun.com", LocalDate.of(2016, 12, 8), LocalTime.of(20, 0), "the_table")));
//        recreate index after drop
        template.indexOps(Restaurant.class).ensureIndex(new GeospatialIndex("location").typed(GEO_2DSPHERE));
        template.indexOps(Restaurant.class).ensureIndex(new Index("ownerEmail", ASC));
        LOGGER.info("end of DB population...");
    }
}
