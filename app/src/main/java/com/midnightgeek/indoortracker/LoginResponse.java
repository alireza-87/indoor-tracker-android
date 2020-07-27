package com.midnightgeek.indoortracker;

public class LoginResponse {
    private String message;
    private LoginUser result;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LoginUser getResult() {
        return result;
    }

    public void setResult(LoginUser result) {
        this.result = result;
    }
}
