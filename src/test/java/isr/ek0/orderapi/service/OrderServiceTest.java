package isr.ek0.orderapi.service;

import isr.ek0.orderapi.model.Order;
import org.junit.Test;

import java.util.List;

import static isr.ek0.orderapi.util.OrderUtil.NEW_ORDER;
import static isr.ek0.orderapi.util.RestaurantUtil.ADMIN1_RESTAURANT1;
import static isr.ek0.orderapi.util.UsersUtil.ADMIN_1;
import static isr.ek0.orderapi.util.UsersUtil.USER_1;

public class OrderServiceTest extends BaseServiceTest{

    @Test
    public void testSave() {
        orderService.save(USER_1.getEmail(), NEW_ORDER);
    }

    @Test
    public void testGetAll() {
        List<Order> all = orderService.getAll(USER_1.getEmail());
        System.out.println(all);
    }

    @Test
    public void testGetAllByRestaurantName() {
        List<Order> all = orderService.getAllByRestaurantName(ADMIN_1.getEmail(), ADMIN1_RESTAURANT1.getName());
        System.out.println(all);
    }
}