package isr.ek0.bookingapi.web;

import isr.ek0.bookingapi.model.User;
import isr.ek0.bookingapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    @RequestMapping
    public List<User> users() {
        return service.getAll();
    }

    // TODO: 30.11.2016 PRE-AUTHORIZE GETUSER
}
