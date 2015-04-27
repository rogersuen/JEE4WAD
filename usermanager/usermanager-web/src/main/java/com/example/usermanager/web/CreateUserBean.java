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
    private String firstName;
    private String lastName;
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String createUser() {
        User user = new User(email, firstName, lastName, password);
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
