package isr.ek0.bookingapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.core.MongoTemplate;

import static isr.ek0.bookingapi.util.db.Populator.populateDB;
import static isr.ek0.bookingapi.web.webutil.JsonUtil.getMapper;

@SpringBootApplication
@EnableCaching
@Import({SecurityConfiguration.class, CorsConfiguration.class,PersistenceConfig.class})
public class Application {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
        MongoTemplate template = context.getBean(MongoTemplate.class);
        populateDB(template);
    }

    @Primary
    @Bean
    public ObjectMapper objectMapper() {
        return getMapper();
    }
}
