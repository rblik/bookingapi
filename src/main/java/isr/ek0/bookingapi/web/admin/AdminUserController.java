package isr.ek0.bookingapi.web.admin;

import isr.ek0.bookingapi.model.User;
import isr.ek0.bookingapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/users")
public class AdminUserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAll();
    }

    // TODO: 30.11.2016 PRE-AUTHORIZE GETUSER
}
