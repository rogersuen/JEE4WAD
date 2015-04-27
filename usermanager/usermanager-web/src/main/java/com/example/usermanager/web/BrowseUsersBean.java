package com.example.usermanager.web;

import com.example.usermanager.model.User;
import com.example.usermanager.model.impl.mem.InMemoryUserManager;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

@Named
@RequestScoped
public class BrowseUsersBean {

    public List<User> getAllUsers() {
        return InMemoryUserManager.getInstance().getAllUsers();
    }
}
