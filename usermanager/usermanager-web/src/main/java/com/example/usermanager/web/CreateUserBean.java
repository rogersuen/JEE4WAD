package com.example.usermanager.web;

import com.example.usermanager.model.User;
import com.example.usermanager.model.UserException;
import com.example.usermanager.model.impl.mem.InMemoryUserManager;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named
@RequestScoped
public class CreateUserBean {
    
    private String email;
    private String displayName;
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = (email == null ? null : email.trim().toLowerCase());
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String createUser() {
        User user = new User(email, displayName, password);
        try {
            InMemoryUserManager.getInstance().addUser(user);
        } catch (UserException ue) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, ue.getMessage(), null);
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return null;
        }

        return "BrowseUsers?faces-redirect=true";
    }
}
