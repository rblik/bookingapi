package isr.ek0.bookingapi.web.user;

import isr.ek0.bookingapi.model.User;
import isr.ek0.bookingapi.service.BookingService;
import isr.ek0.bookingapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static isr.ek0.bookingapi.AuthorizedUser.logged_admin_email;
import static isr.ek0.bookingapi.AuthorizedUser.logged_user_email;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private UserService service;

    @Autowired
    private BookingService bookingService;

    @GetMapping
    public User getProfile() {
        return service.get(logged_admin_email);
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public User register(@RequestBody User user) {
        return service.save(user);
    }

    @DeleteMapping
    public void deleteProfile() {
        service.delete(logged_user_email);
        bookingService.deleteAll(logged_user_email);
    }
}
