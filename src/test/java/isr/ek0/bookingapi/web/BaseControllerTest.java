package isr.ek0.bookingapi.web;

import isr.ek0.bookingapi.service.RestaurantService;
import org.apache.tomcat.util.codec.binary.Base64;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit4.SpringRunner;

import static isr.ek0.bookingapi.testutils.PopulateUtil.repopulateDB;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@SuppressWarnings("SpringJavaAutowiredMembersInspection")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class BaseControllerTest {

    protected static final Logger LOGGER = LoggerFactory.getLogger(BaseControllerTest.class);
    @Autowired
    protected MongoTemplate mongoTemplate;
    @Autowired
    protected RestaurantService restaurantService;
    @Autowired
    protected TestRestTemplate restTemplate;

    @Before
    public void init() {
        restaurantService.evictCache();
        repopulateDB(mongoTemplate);
    }

//    http://stackoverflow.com/questions/21920268/basic-authentication-for-rest-api-using-spring-resttemplate
    protected HttpHeaders setBasicAuth(String email, String pass) {
        String plainCreds = email+":"+pass;
        byte[] plainCredsBytes = plainCreds.getBytes();
        byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
        String base64Creds = new String(base64CredsBytes);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Creds);
        headers.setContentType(APPLICATION_JSON);
        return headers;
    }
}
