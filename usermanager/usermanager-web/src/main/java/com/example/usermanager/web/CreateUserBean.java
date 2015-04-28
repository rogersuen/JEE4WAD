package com.example.usermanager.web;

import com.example.usermanager.model.User;
import com.example.usermanager.model.UserException;
import com.example.usermanager.model.UserManager;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named
@RequestScoped
public class CreateUserBean {

    @EJB
    private UserManager userManager;

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

    public void checkAvailability() {
        if (email == null) {
            return;
        }

        FacesMessage msg;
        if (isEmailAvailable(email)) {
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "The email is available.", null);
        } else {
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "The email is NOT available.", null);
        }

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public String createUser() {
        User user = new User(email, displayName, password);

        try {
            userManager.addUser(user);
        } catch (UserException e) {
            FacesMessage msg = new FacesMessage(
                    FacesMessage.SEVERITY_ERROR,
                    e.getMessage(),
                    null);
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return null;
        }
        return "BrowseUsers?faces-redirect=true";
    }

    private boolean isEmailAvailable(String email) {
        return (userManager.findUserByEmail(email) == null);
    }
}
