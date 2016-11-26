package isr.ek0.orderapi.util;

import isr.ek0.orderapi.model.Order;

import java.util.List;

import static com.google.common.collect.ImmutableList.of;
import static isr.ek0.orderapi.util.RestaurantUtil.ADMIN1_RESTAURANT1;
import static isr.ek0.orderapi.util.UsersUtil.USER_1;
import static isr.ek0.orderapi.util.UsersUtil.USER_2;
import static java.time.LocalDateTime.now;
import static java.time.temporal.ChronoField.HOUR_OF_DAY;
import static java.time.temporal.ChronoField.MINUTE_OF_HOUR;
import static java.time.temporal.ChronoUnit.MINUTES;

public class OrderUtil {
    public static final Order ORDER_1 = new Order(now().with(HOUR_OF_DAY, 18).with(MINUTE_OF_HOUR, 0).truncatedTo(MINUTES), USER_1.getEmail(), ADMIN1_RESTAURANT1.getName());
    public static final Order ORDER_2 = new Order(now().with(HOUR_OF_DAY, 20).with(MINUTE_OF_HOUR, 0).truncatedTo(MINUTES), USER_2.getEmail(), ADMIN1_RESTAURANT1.getName());
    public static final List<Order> ORDERS = of(ORDER_1, ORDER_2);
    public static final Order NEW_ORDER = new Order(now().with(HOUR_OF_DAY, 21).with(MINUTE_OF_HOUR, 0).truncatedTo(MINUTES), ADMIN1_RESTAURANT1.getName());
}
