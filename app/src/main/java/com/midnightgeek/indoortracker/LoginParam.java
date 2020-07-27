package com.midnightgeek.indoortracker;

public class LoginParam {
    String email,password;

    public LoginParam(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public LoginParam() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
