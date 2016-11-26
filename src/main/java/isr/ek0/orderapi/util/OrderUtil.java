package isr.ek0.orderapi.util;

import isr.ek0.orderapi.model.Order;
import isr.ek0.orderapi.model.OrderId;

import java.time.LocalTime;
import java.util.List;

import static com.google.common.collect.ImmutableList.of;
import static isr.ek0.orderapi.util.RestaurantUtil.ADMIN1_RESTAURANT1;
import static isr.ek0.orderapi.util.UsersUtil.USER_1;
import static isr.ek0.orderapi.util.UsersUtil.USER_2;
import static java.time.LocalDate.now;

public class OrderUtil {
    public static final Order ORDER_1 = new Order(new OrderId(USER_1.getEmail(), now()), LocalTime.of(18, 0), ADMIN1_RESTAURANT1.getName());
    public static final Order ORDER_2 = new Order(new OrderId(USER_2.getEmail(), now()), LocalTime.of(20, 0), ADMIN1_RESTAURANT1.getName());
    public static final List<Order> ORDERS = of(ORDER_1, ORDER_2);
    public static final Order NEW_ORDER = new Order(now().plusDays(1), LocalTime.of(21, 0), ADMIN1_RESTAURANT1.getName());
    public static final Order NEW_ORDER_SAME_DAY = new Order(now().plusDays(0), LocalTime.of(21, 0), ADMIN1_RESTAURANT1.getName());
}
