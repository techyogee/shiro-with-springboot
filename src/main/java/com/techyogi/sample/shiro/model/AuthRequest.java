package com.techyogi.sample.shiro.model;

public class AuthRequest {
    private String username;
    private String password;

    //create a default constructor
    public AuthRequest() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //create toString method
    @Override
    public String toString() {
        return "AuthRequest [username=" + username + ", password=" + password + "]";
    }
}
