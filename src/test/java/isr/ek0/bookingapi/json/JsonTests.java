package isr.ek0.bookingapi.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import isr.ek0.bookingapi.model.Booking;
import isr.ek0.bookingapi.model.Restaurant;
import isr.ek0.bookingapi.model.User;
import org.junit.Test;

import java.io.IOException;

import static isr.ek0.bookingapi.web.json.JsonUtil.getMapper;

public class JsonTests {

    private static final ObjectMapper mapper = getMapper();

    @Test
    public void serializeTest() throws IOException {
        String bookingJson = "{\"date\":\"1989-12-18\",\"time\":\"15:00\",\"restaurantName\":\"Bugi-Vugi\"}";
        Booking booking = mapper.readValue(bookingJson, Booking.class);
        System.out.println(booking);
        String restaurantJson = "{\"name\":\"The Table\",\"location\":{\"x\":\"-40.0\",\"y\":\"-35.0\"},\"menu\":[{\"description\":\"Sushi\",\"preparingTime\":30,\"price\":5.0},{\"description\":\"Borsch\",\"preparingTime\":40,\"price\":7.0},{\"description\":\"Steak\",\"preparingTime\":35,\"price\":8.0}],\"ownerEmail\":\"admin@gmail.com\",\"openTime\":\"08:00\",\"closeTime\":\"23:00\"}";
        Restaurant restaurant = mapper.readValue(restaurantJson, Restaurant.class);
        System.out.println(restaurant);
        String userJson = "{\"email\":\"user@gmail.com\",\"name\":\"User\",\"password\":\"password\"}";
        User user = mapper.readValue(userJson, User.class);
        System.out.println(user);
    }
}
