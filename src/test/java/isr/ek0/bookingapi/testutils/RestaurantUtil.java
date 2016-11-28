package isr.ek0.bookingapi.testutils;

import isr.ek0.bookingapi.model.Meal;
import isr.ek0.bookingapi.model.Restaurant;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

import java.util.List;

import static com.google.common.collect.ImmutableList.of;
import static isr.ek0.bookingapi.testutils.UsersUtil.ADMIN_1;
import static java.lang.Double.compare;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

public class RestaurantUtil {
    public static final Meal A_1_R_1_MEAL_1 = new Meal("Sushi", 30, 5.0);
    public static final Meal A_1_R_1_MEAL_2 = new Meal("Borsch", 40, 7.0);
    public static final Meal A_1_R_1_MEAL_3 = new Meal("Steak", 35, 8.0);
    public static final Restaurant ADMIN1_RESTAURANT1 = new Restaurant("The Table", new GeoJsonPoint(-40.0, -35.0),
            asList(
                    A_1_R_1_MEAL_1,
                    A_1_R_1_MEAL_2,
                    A_1_R_1_MEAL_3
            ), ADMIN_1.getEmail());
    public static final Restaurant ADMIN1_RESTAURANT2 = new Restaurant("Aizle", new GeoJsonPoint(-41.0, -32.0),
            asList(
                    new Meal("Meshed Potatoes", 20, 4.0),
                    new Meal("Soup", 30, 5.0),
                    new Meal("Caesar", 35, 5.0)
            ), ADMIN_1.getEmail());
    public static final Restaurant ADMIN2_RESTAURANT1 = new Restaurant("Oink", new GeoJsonPoint(-50.0, -33.0),
            asList(
                    new Meal("Pasta", 20, 5.0),
                    new Meal("Soup", 40, 6.0),
                    new Meal("Burger", 20, 6.0)
            ), UsersUtil.ADMIN_2.getEmail());
    public static final Restaurant ADMIN2_RESTAURANT2 = new Restaurant("Pickles", new GeoJsonPoint(-30.0, -38.0),
            asList(
                    new Meal("Kebab", 20, 5.0),
                    new Meal("Falafel", 40, 6.0),
                    new Meal("Djahnun", 20, 6.0)
            ), UsersUtil.ADMIN_2.getEmail());
    public static final Restaurant NEW_RESTAURANT = new Restaurant("Fine Eat", new GeoJsonPoint(10, -40),
            asList(
                    new Meal("Porridge", 30, 10.0),
                    new Meal("Soup", 45, 15.0),
                    new Meal("Grecha", 15, 20.0)));

    public static final Restaurant NEW_RESTAURANT_SAVED = new Restaurant("Fine Eat", null, null, ADMIN_1.getEmail());
    public static final Restaurant NEW_RESTAURANT_DUBLICATE = new Restaurant("Aizle", null, null, ADMIN_1.getEmail());
    public static final List<Restaurant> RESTAURANTS = of(ADMIN1_RESTAURANT1, ADMIN1_RESTAURANT2, ADMIN2_RESTAURANT1, ADMIN2_RESTAURANT2);
    public static final List<Restaurant> RESTAURANTS_SORTED_BY_NAME = of(ADMIN1_RESTAURANT1, ADMIN1_RESTAURANT2, ADMIN2_RESTAURANT1, ADMIN2_RESTAURANT2).stream().sorted((o1, o2) -> o1.getName().compareTo(o2.getName())).collect(toList());
    public static final List<Restaurant> RESTAURANTS_SORTED_BY_LOCATION = RESTAURANTS.stream().sorted((o1, o2) -> compare(Math.hypot(o1.getLocation().getX(), o1.getLocation().getY()), Math.hypot(o2.getLocation().getX(), o2.getLocation().getY()))).collect(toList());
    public static final List<Restaurant> RESTAURANTS_SORTED_BY_LOCATION_WITH_DISTANCE = RESTAURANTS_SORTED_BY_LOCATION.subList(0, 3);
    public static final List<Restaurant> ADMIN1_RESTAURANTS = of(ADMIN1_RESTAURANT1, ADMIN1_RESTAURANT2);
    public static final List<Restaurant> RESTAURANTS_WITH_NEW = asList(ADMIN1_RESTAURANT1, ADMIN1_RESTAURANT2, ADMIN2_RESTAURANT1, ADMIN2_RESTAURANT2, NEW_RESTAURANT_SAVED).stream().sorted((o1, o2) -> o1.getName().compareTo(o2.getName())).collect(toList());
    public static final List<Restaurant> ADMIN2_RESTAURANTS = of(ADMIN2_RESTAURANT1, ADMIN2_RESTAURANT2);
    public static final Meal NEW_MEAL_1 = new Meal("Salad", 10, 10.0);
    public static final Meal NEW_MEAL_2 = new Meal("Lasagna", 10, 10.0);
    public static final List<Meal> MEALS_WITH_1_NEW = of(A_1_R_1_MEAL_1, A_1_R_1_MEAL_2, A_1_R_1_MEAL_3, NEW_MEAL_1);
    public static final List<Meal> MEALS_WITH_2_NEW = of(A_1_R_1_MEAL_1, A_1_R_1_MEAL_2, A_1_R_1_MEAL_3, NEW_MEAL_1, NEW_MEAL_2);
}
