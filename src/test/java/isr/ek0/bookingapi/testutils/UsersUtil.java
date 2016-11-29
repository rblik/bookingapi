package isr.ek0.bookingapi.testutils;

import isr.ek0.bookingapi.model.User;

import java.util.List;

import static com.google.common.collect.ImmutableList.of;
import static isr.ek0.bookingapi.model.Role.ROLE_ADMIN;
import static isr.ek0.bookingapi.model.Role.ROLE_USER;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

public class UsersUtil {
    public static final User ADMIN_1 = new User("admin@gmail.com", "Admin", "admin", asList(ROLE_USER, ROLE_ADMIN));
    public static final User ADMIN_2 = new User("admin1@gmail.com", "Admin1", "admin", asList(ROLE_USER, ROLE_ADMIN));
    public static final User USER_1 = new User("user@yandex.ru", "User1", "password", singletonList(ROLE_USER));
    public static final User USER_2 = new User("user@fun.com", "User2", "password", singletonList(ROLE_USER));
    public static final User NEW_USER = new User("user@ofice.com", "NewUser", "password");
    public static final User INVALID_USER = new User(null, null, "password");

    public static final List<User> USERS = of(ADMIN_1, ADMIN_2, USER_1, USER_2);
    public static final List<User> USERS_WITH_NEW = of(ADMIN_1, ADMIN_2, USER_1, USER_2, NEW_USER);
}
