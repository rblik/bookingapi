package isr.ek0.orderapi.util;

import isr.ek0.orderapi.model.Order;

import java.time.LocalDateTime;
import java.util.List;

import static com.google.common.collect.ImmutableList.of;
import static isr.ek0.orderapi.util.RestaurantUtil.ADMIN1_RESTAURANT1;
import static isr.ek0.orderapi.util.UsersUtil.USER_1;
import static isr.ek0.orderapi.util.UsersUtil.USER_2;
import static java.time.Month.NOVEMBER;

public class OrderUtil {
    public static final Order ORDER_1 = new Order(LocalDateTime.of(2016, NOVEMBER, 26, 12, 0), USER_1.getEmail(), ADMIN1_RESTAURANT1.getName());
    public static final Order ORDER_2 = new Order(LocalDateTime.of(2016, NOVEMBER, 26, 15, 0), USER_2.getEmail(), ADMIN1_RESTAURANT1.getName());
    public static final List<Order> ORDERS = of(ORDER_1, ORDER_2);
    public static final Order NEW_ORDER = new Order(LocalDateTime.of(2016, NOVEMBER, 27, 19, 0), ADMIN1_RESTAURANT1.getName());
}
