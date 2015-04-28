package com.example.usermanager.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

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

    private String email;
    private String displayName;
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
