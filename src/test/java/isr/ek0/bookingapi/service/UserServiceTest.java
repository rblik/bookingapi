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

import static isr.ek0.bookingapi.testutils.UsersUtil.*;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

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
        assertArrayEquals(USERS_WITH_NEW.toArray(), userService.getAll(ADMIN_1.getEmail()).toArray());
    }

    @Test(expected = DuplicateKeyException.class)
    public void testSaveWithDuplicateEmail() {
        userService.save(USER_1);
    }

    @Test
    public void testQueryPlan() {
        Query query = new Query(Criteria.where("_id").is(ADMIN_1.getEmail())).with(new Sort(Sort.Direction.ASC, "_id"));
        DBCollection collection = template.getCollection("users");
        DBCursor cursor = collection.find(query.getQueryObject());
        LOGGER.debug(cursor.explain().toString());
        DBObject queryPlanner = (DBObject)cursor.explain().get("queryPlanner");
        DBObject winningPlan = (DBObject)queryPlanner.get("winningPlan");
        assertEquals("IDHACK", winningPlan.get("stage"));
    }
}