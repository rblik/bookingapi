package isr.ek0.voter.util;

import isr.ek0.voter.model.User;

import java.util.List;

import static isr.ek0.voter.model.Role.ROLE_ADMIN;
import static isr.ek0.voter.model.Role.ROLE_USER;
import static java.util.Arrays.asList;

public class UsersUtil {
    public static final User ADMIN = new User("admin@gmail.com", "Admin", "admin", ROLE_ADMIN);
    public static final User USER_1 = new User("user@yandex.ru", "User1", "password", ROLE_USER);
    public static final User USER_2 = new User("user@fun.com", "User2", "password", ROLE_USER);

    public static final List<User> TEST_USERS = asList(ADMIN, USER_1, USER_2);
}
