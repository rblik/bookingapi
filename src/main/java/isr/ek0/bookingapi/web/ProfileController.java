package isr.ek0.bookingapi.web;

import isr.ek0.bookingapi.model.User;
import isr.ek0.bookingapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import static isr.ek0.bookingapi.AuthorizedUser.logged_admin_email;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private UserService service;

    @GetMapping
    public User getProfile() {
        return service.get(logged_admin_email);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public User register(@RequestBody User user) {
        return service.save(user);
    }
}
