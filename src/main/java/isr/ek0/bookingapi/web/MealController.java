package isr.ek0.bookingapi.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MealController {
    @RequestMapping("/hello")
    public String hello() {
        return "Hello";
    }
}
