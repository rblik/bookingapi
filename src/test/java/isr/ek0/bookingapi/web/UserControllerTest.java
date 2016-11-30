package isr.ek0.bookingapi.web;

import isr.ek0.bookingapi.model.User;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import static isr.ek0.bookingapi.testutils.UsersUtil.USERS;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.springframework.http.HttpStatus.OK;


public class UserControllerTest extends BaseControllerTest {

    @Test
    public void testGetAll() {
        ResponseEntity<User[]> usersEntity = restTemplate.getForEntity("/users", User[].class);
        LOGGER.debug(usersEntity.toString());
        assertEquals(OK, usersEntity.getStatusCode());
        assertEquals(USERS, asList(usersEntity.getBody()));
    }

}