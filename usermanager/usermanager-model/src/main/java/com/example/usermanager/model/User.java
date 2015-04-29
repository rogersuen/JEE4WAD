package com.example.usermanager.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NamedQueries({
    @NamedQuery(name = "User.getAllUsers", query = "SELECT u FROM User u"),
    @NamedQuery(name="User.findUserByEmail", query = "SELECT u FROM User u WHERE u.email = :email")
})
@Entity
@Table(name = "JEE4WAD_USER")
public class User implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull(message = "Email is required.")
    @Size(min = 6, max = 40, message = "Email is invalid.")
    private String email;

    @NotNull(message = "Display name is required.")
    @Size(min = 2, max = 40, message = "Display name must be between {min} - {max} characters long.")
    private String displayName;

    @Size(min = 2, max = 40, message = "Password must be between {min} - {max} characters long.")
    private String password;

    public User() {
    }

    public User(String email, String displayName, String password) {
        this.email = email;
        this.displayName = displayName;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

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
}
