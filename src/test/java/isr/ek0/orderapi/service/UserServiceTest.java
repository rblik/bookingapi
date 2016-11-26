package isr.ek0.orderapi.service;

import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import isr.ek0.orderapi.model.User;
import org.junit.Test;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import static isr.ek0.orderapi.util.UsersUtil.ADMIN_1;
import static isr.ek0.orderapi.util.UsersUtil.USER_1;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class UserServiceTest extends BaseServiceTest{

    @Test
    public void testGet() {
        User userFound = userService.get(ADMIN_1.getEmail());
        assertEquals(ADMIN_1, userFound);
        assertNotEquals(USER_1, userFound);
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