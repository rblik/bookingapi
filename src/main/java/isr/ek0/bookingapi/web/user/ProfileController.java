package isr.ek0.bookingapi.web.user;

import isr.ek0.bookingapi.AuthorizedUser;
import isr.ek0.bookingapi.model.User;
import isr.ek0.bookingapi.service.BookingService;
import isr.ek0.bookingapi.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class ProfileController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProfileController.class);

    @Autowired
    private UserService service;

    @Autowired
    private BookingService bookingService;

    @GetMapping("/profile")
    public User getProfile() {
        String loggedUserEmail = AuthorizedUser.mail();
        LOGGER.info("{} is getting his profile", loggedUserEmail);
        return service.get(loggedUserEmail);
    }

    @PostMapping(value = "/register",consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<User> register(@Valid @RequestBody User user) {
        LOGGER.info("saving new {}", user);
        User userSaved = service.save(user);
        URI profileUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/profile").build().toUri();
        return ResponseEntity.created(profileUri).body(userSaved);
    }

    @DeleteMapping("/profile")
    public void deleteProfile() {
        String loggedUserEmail = AuthorizedUser.mail();
        LOGGER.info("{} is deleting humself", loggedUserEmail);
        service.delete(loggedUserEmail);
        bookingService.deleteAll(loggedUserEmail);
    }
}
