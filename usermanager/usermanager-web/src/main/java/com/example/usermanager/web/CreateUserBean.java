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
    
    private final User user = new User();

    public User getUser() {
        return user;
    }

    public void checkAvailability() {
        if (user.getEmail() == null) {
            return;
        }

        FacesMessage msg;
        if (isEmailAvailable(user.getEmail())) {
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "The email is available.", null);
        } else {
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "The email is NOT available.", null);
        }

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public String createUser() {
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
