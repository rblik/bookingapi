package isr.ek0.bookingapi.testutils;

import isr.ek0.bookingapi.model.Meal;
import isr.ek0.bookingapi.model.Restaurant;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

import java.util.List;

import static com.google.common.collect.ImmutableList.of;
import static java.util.Arrays.asList;

public class RestaurantUtil {

    public static final Restaurant ADMIN1_RESTAURANT1 = new Restaurant("The Table", new GeoJsonPoint(-40.0, -35.0),
            asList(
                    new Meal("Sushi", 30, 5),
                    new Meal("Borsch", 40, 7),
                    new Meal("Steak", 35, 8)
            ), UsersUtil.ADMIN_1.getEmail());
    public static final Restaurant ADMIN1_RESTAURANT2 = new Restaurant("Aizle", new GeoJsonPoint(-41.0, -32.0),
            asList(
                    new Meal("Meshed Potatoes", 20, 4),
                    new Meal("Soup", 30, 5),
                    new Meal("Caesar", 35, 5)
            ), UsersUtil.ADMIN_1.getEmail());
    public static final Restaurant ADMIN2_RESTAURANT1 = new Restaurant("Oink", new GeoJsonPoint(-50.0, -33.0),
            asList(
                    new Meal("Pasta", 20, 5),
                    new Meal("Soup", 40, 6),
                    new Meal("Burger", 20, 6)
            ), UsersUtil.ADMIN_2.getEmail());
    public static final Restaurant ADMIN2_RESTAURANT2 = new Restaurant("Pickles", new GeoJsonPoint(-30.0, -38.0),
            asList(
                    new Meal("Kebab", 20, 5),
                    new Meal("Falafel", 40, 6),
                    new Meal("Djahnun", 20, 6)
            ), UsersUtil.ADMIN_2.getEmail());
    public static final Restaurant NEW_RESTAURANT = new Restaurant("Fine Eat", new GeoJsonPoint(10, -40),
            asList(
                    new Meal("Porridge", 30, 10),
                    new Meal("Soup", 45, 15),
                    new Meal("Grecha", 15, 20)));

    public static final List<Restaurant> RESTAURANTS = of(ADMIN1_RESTAURANT1, ADMIN1_RESTAURANT2, ADMIN2_RESTAURANT1, ADMIN2_RESTAURANT2);
    public static final List<Restaurant> ADMIN1_RESTAURANTS = of(ADMIN1_RESTAURANT1, ADMIN1_RESTAURANT2);
    public static final List<Restaurant> ADMIN2_RESTAURANTS = of(ADMIN2_RESTAURANT1, ADMIN2_RESTAURANT2);
    public static final Meal NEW_MEAL_1 = new Meal("Salad", 10, 10);
    public static final Meal NEW_MEAL_2 = new Meal("Salad", 10, 10);
}
