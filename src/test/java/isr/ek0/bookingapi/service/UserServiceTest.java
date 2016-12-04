package isr.ek0.bookingapi.service;

import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import isr.ek0.bookingapi.model.User;
import isr.ek0.bookingapi.util.exception.NotFoundException;
import org.junit.Test;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import javax.validation.ConstraintViolationException;

import static isr.ek0.bookingapi.testutils.UsersUtil.*;
import static org.junit.Assert.*;

public class UserServiceTest extends BaseServiceTest{

    @Test
    public void testGet() {
        User userFound = userService.get(ADMIN_1.getEmail());
        assertEquals(ADMIN_1, userFound);
        assertNotEquals(USER_1, userFound);
    }

    @Test(expected = NotFoundException.class)
    public void testGetNotFound() {
        userService.get("asd");
    }

    @Test
    public void testSave() {
        userService.save(NEW_USER);
        assertArrayEquals(USERS_WITH_NEW.toArray(), userService.getAll().toArray());
    }

    @Test(expected = DuplicateKeyException.class)
    public void testSaveWithDuplicateEmail() {
        userService.save(USER_1);
    }

    @Test(expected = ConstraintViolationException.class)
    public void testSaveNotValid() {
        userService.save(INVALID_USER);
    }

    @Test(expected = ConstraintViolationException.class)
    public void testSaveNotValidEmail() {
        userService.save(INVALID_EMAIL_USER);
    }

    @Test
    public void testDelete() {
        userService.delete(USER_1.getEmail());
    }

//    @Test disabled for travis-ci
    public void testQueryPlan() {
        Query query = new Query(Criteria.where("_id").is(ADMIN_1.getEmail())).with(new Sort(Sort.Direction.ASC, "_id"));
        DBCollection collection = mongoTemplate.getCollection("users");
        DBCursor cursor = collection.find(query.getQueryObject());
        LOGGER.debug(cursor.explain().toString());
        DBObject queryPlanner = (DBObject)cursor.explain().get("queryPlanner");
        DBObject winningPlan = (DBObject)queryPlanner.get("winningPlan");
        assertEquals("IDHACK", winningPlan.get("stage"));
    }
}