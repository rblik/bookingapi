package isr.ek0.bookingapi.testutils;

import isr.ek0.bookingapi.model.Meal;
import isr.ek0.bookingapi.model.Restaurant;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

import java.time.LocalTime;
import java.util.List;

import static com.google.common.collect.ImmutableList.of;
import static isr.ek0.bookingapi.testutils.UsersUtil.ADMIN_1;
import static isr.ek0.bookingapi.testutils.UsersUtil.ADMIN_2;
import static java.lang.Double.compare;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

public class RestaurantUtil {
    public static final Meal A_1_R_1_MEAL_1 = new Meal("Sushi", 30, 5.0);
    public static final Meal A_1_R_1_MEAL_2 = new Meal("Borsch", 40, 7.0);
    public static final Meal A_1_R_1_MEAL_3 = new Meal("Steak", 35, 8.0);
    public static final Meal A_1_R_2_MEAL_1 = new Meal("Meshed Potatoes", 20, 4.0);
    public static final Meal A_1_R_2_MEAL_2 = new Meal("Soup", 30, 5.0);
    public static final Meal A_1_R_2_MEAL_3 = new Meal("Caesar", 35, 5.0);
    public static final Meal A_2_R_1_MEAL_1 = new Meal("Pasta", 20, 5.0);
    public static final Meal A_2_R_1_MEAL_2 = new Meal("Soup", 40, 6.0);
    public static final Meal A_2_R_1_MEAL_3 = new Meal("Burger", 20, 6.0);
    public static final Meal A_2_R_2_MEAL_1 = new Meal("Kebab", 20, 5.0);
    public static final Meal A_2_R_2_MEAL_2 = new Meal("Falafel", 40, 6.0);
    public static final Meal A_2_R_2_MEAL_3 = new Meal("Djahnun", 20, 6.0);
    public static final Meal NEW_RESTAURANT_MEAL_1 = new Meal("Porridge", 30, 10.0);
    public static final Meal NEW_RESTAURANT_MEAL_2 = new Meal("Soup", 45, 15.0);
    public static final Meal NEW_RESTAURANT_MEAL_3 = new Meal("Grecha", 15, 20.0);
    public static final Meal NEW_RESTAURANT_MEAL_3_NOT_VALID = new Meal(null, 15, 20.0);
    public static final List<Meal> A_1_R_1_MEALS = of(A_1_R_1_MEAL_1, A_1_R_1_MEAL_2, A_1_R_1_MEAL_3);
    public static final List<Meal> A_1_R_2_MEALS = of(A_1_R_2_MEAL_1, A_1_R_2_MEAL_2, A_1_R_2_MEAL_3);
    public static final List<Meal> A_2_R_1_MEALS = of(A_2_R_1_MEAL_1, A_2_R_1_MEAL_2, A_2_R_1_MEAL_3);
    public static final List<Meal> A_2_R_2_MEALS = of(A_2_R_2_MEAL_1, A_2_R_2_MEAL_2, A_2_R_2_MEAL_3);
    public static final List<Meal> NEW_RESTAURANT_MEALS = of(NEW_RESTAURANT_MEAL_1, NEW_RESTAURANT_MEAL_2, NEW_RESTAURANT_MEAL_3);
    public static final List<Meal> NEW_RESTAURANT_MEALS_NOT_VALID = of(NEW_RESTAURANT_MEAL_1, NEW_RESTAURANT_MEAL_2, NEW_RESTAURANT_MEAL_3_NOT_VALID);
    public static final Restaurant ADMIN1_RESTAURANT1 = new Restaurant("the_table", new GeoJsonPoint(-40.0, -35.0), A_1_R_1_MEALS, ADMIN_1.getEmail(), LocalTime.of(8, 0), LocalTime.of(23, 0));
    public static final Restaurant ADMIN1_RESTAURANT2 = new Restaurant("aizle", new GeoJsonPoint(-41.0, -32.0), A_1_R_2_MEALS, ADMIN_1.getEmail(), LocalTime.of(7, 0), LocalTime.of(22, 0));
    public static final Restaurant ADMIN2_RESTAURANT1 = new Restaurant("oink", new GeoJsonPoint(-50.0, -33.0), A_2_R_1_MEALS, ADMIN_2.getEmail(), LocalTime.of(10, 0), LocalTime.of(23, 0));
    public static final Restaurant ADMIN2_RESTAURANT2 = new Restaurant("pickles", new GeoJsonPoint(-30.0, -38.0), A_2_R_2_MEALS, ADMIN_2.getEmail(), LocalTime.of(9, 0), LocalTime.of(23, 0));
    public static final Restaurant NEW_RESTAURANT = new Restaurant("fine_eat", new GeoJsonPoint(10, -40), NEW_RESTAURANT_MEALS, LocalTime.of(6, 0), LocalTime.of(20, 0));
    public static final Restaurant NEW_RESTAURANT_NOT_VALID = new Restaurant("", new GeoJsonPoint(10, -40), NEW_RESTAURANT_MEALS, null, null);
    public static final Restaurant NEW_RESTAURANT_NOT_VALID_MEAL = new Restaurant("NotValid", new GeoJsonPoint(10, -40), NEW_RESTAURANT_MEALS_NOT_VALID, LocalTime.of(6, 0), LocalTime.of(21, 0));
    public static final Restaurant NEW_RESTAURANT_SAVED = new Restaurant("fine_eat", null, null, ADMIN_1.getEmail(), LocalTime.of(8, 0), LocalTime.of(23, 0));
    public static final Restaurant NEW_RESTAURANT_DUPLICATE = new Restaurant("aizle", new GeoJsonPoint(10, -40), null, ADMIN_1.getEmail(), LocalTime.of(7, 0), LocalTime.of(22, 0));
    public static final List<Restaurant> RESTAURANTS = of(ADMIN1_RESTAURANT1, ADMIN1_RESTAURANT2, ADMIN2_RESTAURANT1, ADMIN2_RESTAURANT2);
    public static final List<Restaurant> RESTAURANTS_SORTED_BY_NAME = of(ADMIN1_RESTAURANT1, ADMIN1_RESTAURANT2, ADMIN2_RESTAURANT1, ADMIN2_RESTAURANT2).stream().sorted((o1, o2) -> o1.getName().compareTo(o2.getName())).collect(toList());
    public static final List<Restaurant> RESTAURANTS_SORTED_BY_NAME_REVERSED = of(ADMIN1_RESTAURANT1, ADMIN1_RESTAURANT2, ADMIN2_RESTAURANT1, ADMIN2_RESTAURANT2).stream().sorted((o1, o2) -> o2.getName().compareTo(o1.getName())).collect(toList());
    public static final List<Restaurant> RESTAURANTS_SORTED_BY_LOCATION = RESTAURANTS.stream().sorted((o1, o2) -> compare(Math.hypot(o1.getLocation().getX(), o1.getLocation().getY()), Math.hypot(o2.getLocation().getX(), o2.getLocation().getY()))).collect(toList());
    public static final List<Restaurant> RESTAURANTS_SORTED_BY_LOCATION_WITH_DISTANCE = RESTAURANTS_SORTED_BY_LOCATION.subList(0, 3);
    public static final List<Restaurant> ADMIN1_RESTAURANTS = of(ADMIN1_RESTAURANT1, ADMIN1_RESTAURANT2);
    public static final List<Restaurant> RESTAURANTS_WITH_NEW = asList(ADMIN1_RESTAURANT1, ADMIN1_RESTAURANT2, ADMIN2_RESTAURANT1, ADMIN2_RESTAURANT2, NEW_RESTAURANT_SAVED).stream().sorted((o1, o2) -> o1.getName().compareTo(o2.getName())).collect(toList());
    public static final List<Restaurant> ADMIN2_RESTAURANTS = of(ADMIN2_RESTAURANT1, ADMIN2_RESTAURANT2);
    public static final Meal NEW_MEAL_1 = new Meal("Salad", 10, 10.0);
    public static final Meal NEW_MEAL_2 = new Meal("Lasagna", 10, 10.0);
    public static final Meal NEW_MEAL_NOT_VALID = new Meal(null, 10, 10.0);
    public static final List<Meal> MEALS_WITH_1_NEW = of(A_1_R_1_MEAL_1, A_1_R_1_MEAL_2, A_1_R_1_MEAL_3, NEW_MEAL_1);
    public static final List<Meal> MEALS_WITH_2_NEW = of(A_1_R_1_MEAL_1, A_1_R_1_MEAL_2, A_1_R_1_MEAL_3, NEW_MEAL_1, NEW_MEAL_2);
}
