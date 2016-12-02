package isr.ek0.bookingapi.web.admin;

import isr.ek0.bookingapi.model.User;
import isr.ek0.bookingapi.web.BaseControllerTest;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import static isr.ek0.bookingapi.testutils.UsersUtil.USERS;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.springframework.http.HttpStatus.OK;

public class AdminUserControllerTest extends BaseControllerTest {

    @Test
    public void testGetAllUsers() {
        ResponseEntity<User[]> usersEntity = restTemplate.getForEntity("/admin/users", User[].class);
        LOGGER.debug(usersEntity.toString());
        assertEquals(OK, usersEntity.getStatusCode());
        assertEquals(USERS, asList(usersEntity.getBody()));
    }
}
