package com.example.usermanager.model;

public class User {
    
    private String email;
    private String firstName;
    private String lastName;
    private String password;

    public User() {
    }

    public User(String email, String firstName, String lastName, String password) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }

    public User(User user) {
        if (user == null) {
            throw new IllegalArgumentException("user cannot be null");
        }

        this.email = user.email;
        this.firstName = user.firstName;
        this.lastName = user.lastName;
        this.password = user.password;
    }

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
}
