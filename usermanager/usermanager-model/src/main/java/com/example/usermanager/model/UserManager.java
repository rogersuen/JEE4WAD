package com.example.usermanager.model;

import java.util.List;

public interface UserManager {

    List<User> getAllUsers();
    
    User findUserByEmail(String email);
    
    User addUser(User user);
}
