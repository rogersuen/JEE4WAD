package com.example.usermanager.model.impl.mem;

import com.example.usermanager.model.User;
import com.example.usermanager.model.UserException;
import com.example.usermanager.model.UserManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryUserManager implements UserManager {

    private static final UserManager INSTANCE = new InMemoryUserManager();

    static {
        InMemoryUserManager.getInstance().addUser(
                new User(
                        "admin@example.com",
                        "admin",
                        "admin",
                        null));
    }

    private final Map<String, User> users = new HashMap<>();

    public static UserManager getInstance() {
        return INSTANCE;
    }

    @Override
    public List<User> getAllUsers() {
        if (users.isEmpty()) {
            return Collections.emptyList();
        } else {
            List<User> ret = new ArrayList<>(users.size());
            for (User user : users.values()) {
                ret.add(new User(user));
            }
            return ret;
        }
    }

    @Override
    public User findUserByEmail(String email) {
        if (email == null) {
            throw new IllegalArgumentException("email cannot be null");
        }

        User user = users.get(email);
        if (user == null) {
            return null;
        } else {
            return new User(user);
        }
    }

    @Override
    public User addUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("user cannot be null");
        }

        if (findUserByEmail(user.getEmail()) != null) {
            throw new UserException("A user with the email address already exists.");
        }

        users.put(user.getEmail(), new User(user));

        return new User(users.get(user.getEmail()));
    }
}
