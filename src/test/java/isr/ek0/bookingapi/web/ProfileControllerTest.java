package isr.ek0.bookingapi.web;

import isr.ek0.bookingapi.model.User;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import static isr.ek0.bookingapi.testutils.JsonUtil.userJson;
import static isr.ek0.bookingapi.testutils.UsersUtil.ADMIN_1;
import static isr.ek0.bookingapi.testutils.UsersUtil.NEW_USER;
import static java.util.Collections.emptyMap;
import static org.junit.Assert.assertEquals;
import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpStatus.OK;

public class ProfileControllerTest extends BaseControllerTest {

    @Test
    public void testGetAuthorisedUser() {
        ResponseEntity<User> responseEntity = restTemplate.getForEntity("/profile", User.class);
        LOGGER.info(responseEntity.toString());
        assertEquals(OK, responseEntity.getStatusCode());
        assertEquals(ADMIN_1, responseEntity.getBody());
    }

    @Test
    public void testRegisterUser() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(userJson, headers);
        ResponseEntity<User> responseEntity = restTemplate.exchange("/profile", POST, entity, User.class, emptyMap());
        LOGGER.info(responseEntity.toString());
        assertEquals(NEW_USER, responseEntity.getBody());
    }

    @Test
    public void testDeleteUser() {
        ResponseEntity<Void> responseEntity = restTemplate.exchange("/profile", DELETE, null, Void.class, emptyMap());
        LOGGER.info(responseEntity.toString());
        assertEquals(OK, responseEntity.getStatusCode());
    }
}