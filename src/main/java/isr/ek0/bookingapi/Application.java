package isr.ek0.bookingapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.event.ValidatingMongoEventListener;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import static com.mongodb.WriteConcern.MAJORITY;
import static isr.ek0.bookingapi.util.db.Populator.populateDB;
import static isr.ek0.bookingapi.web.webutil.JsonUtil.getMapper;

@SpringBootApplication
@EnableCaching
@Import(SecurityConfiguration.class)
public class Application {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
        MongoTemplate template = context.getBean(MongoTemplate.class);
        populateDB(template);
    }

    @Autowired
    private MongoDbFactory factory;

    @Bean
    public MongoTemplate mongoTemplate() {
        MongoTemplate template = new MongoTemplate(factory);
        template.setWriteConcern(MAJORITY);
        return template;
    }

    @Bean
    public ValidatingMongoEventListener validatingMongoEventListener() {
        return new ValidatingMongoEventListener(validator());
    }

    @Bean
    public LocalValidatorFactoryBean validator() {
        return new LocalValidatorFactoryBean();
    }

//    @Primary
    @Bean
    public ObjectMapper objectMapper() {
        return getMapper();
    }
}
