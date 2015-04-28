package com.example.usermanager.model;

public class User {

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

    public User(User user) {
        if (user == null) {
            throw new IllegalArgumentException("user cannot be null");
        }

        this.email = user.email;
        this.displayName = user.displayName;
        this.password = user.password;
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
