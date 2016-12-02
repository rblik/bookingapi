package isr.ek0.bookingapi.web.user;

import isr.ek0.bookingapi.model.User;
import isr.ek0.bookingapi.util.exception.ErrorInfo;
import isr.ek0.bookingapi.web.BaseControllerTest;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import static isr.ek0.bookingapi.testutils.JsonUtil.userJson;
import static isr.ek0.bookingapi.testutils.JsonUtil.userJsonNotValid;
import static isr.ek0.bookingapi.testutils.UsersUtil.ADMIN_1;
import static isr.ek0.bookingapi.testutils.UsersUtil.NEW_USER;
import static java.util.Collections.emptyMap;
import static org.junit.Assert.assertEquals;
import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CREATED;
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
        assertEquals(CREATED, responseEntity.getStatusCode());
        assertEquals(NEW_USER, responseEntity.getBody());
    }

    @Test
    public void testRegisterUserNotValid() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(userJsonNotValid, headers);
        ResponseEntity<ErrorInfo> responseEntity = restTemplate.exchange("/profile", POST, entity, ErrorInfo.class, emptyMap());
        LOGGER.info(responseEntity.toString());
        assertEquals(BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("ValidationException", responseEntity.getBody().getCause());
    }

    @Test
    public void testDeleteUser() {
        ResponseEntity<Void> responseEntity = restTemplate.exchange("/profile", DELETE, null, Void.class, emptyMap());
        LOGGER.info(responseEntity.toString());
        assertEquals(OK, responseEntity.getStatusCode());
    }
}