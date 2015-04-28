package com.example.usermanager.web;

import com.example.usermanager.model.User;
import com.example.usermanager.model.UserManager;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

@Named
@RequestScoped
public class BrowseUsersBean {

    @EJB
    private UserManager userManager;
    
    public List<User> getAllUsers() {
        return userManager.getAllUsers();
    }
}
