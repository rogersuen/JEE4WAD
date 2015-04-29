package com.example.usermanager.web;

import com.example.usermanager.model.User;
import com.example.usermanager.model.UserException;
import com.example.usermanager.model.UserManager;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Named
@RequestScoped
public class CreateUserBean {

    @EJB
    private UserManager userManager;

    @NotNull(message = "Email is required.")
    @Size(min = 6, max = 40, message = "Email is invalid.")
    private String email;

    @NotNull(message = "Display name is required.")
    @Size(min = 2, max = 40, message = "Display name must be between {min} - {max} characters long.")
    private String displayName;

    @Size(min = 2, max = 40, message = "Password must be between {min} - {max} characters long.")
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
