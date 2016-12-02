package isr.ek0.bookingapi.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import isr.ek0.bookingapi.model.Booking;
import isr.ek0.bookingapi.model.Restaurant;
import isr.ek0.bookingapi.model.User;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static isr.ek0.bookingapi.testutils.JsonUtil.bookingJson;
import static isr.ek0.bookingapi.testutils.JsonUtil.restaurantJson;
import static isr.ek0.bookingapi.testutils.JsonUtil.userJson;
import static isr.ek0.bookingapi.web.webutil.JsonUtil.getMapper;

public class JsonTests {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonTests.class);
    private static final ObjectMapper mapper = getMapper();

    @Test
    public void serializeTest() throws IOException {
        Booking booking = mapper.readValue(bookingJson, Booking.class);
        LOGGER.debug(mapper.writeValueAsString(booking));
        Restaurant restaurant = mapper.readValue(restaurantJson, Restaurant.class);
        LOGGER.debug(mapper.writeValueAsString(restaurant));
        User user = mapper.readValue(userJson, User.class);
        LOGGER.debug(mapper.writeValueAsString(user));
        user.setName(null);
        LOGGER.debug(mapper.writeValueAsString(user));
    }
}
