package com.midnightgeek.indoortracker;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LoginUser {
    private String name;

    private String surename;

    private String rule;

    private String tell;

    private String email;

    private String tokenid;

    private String uid;

    private String password;

    public LoginUser() {
    }

    public LoginUser(String name, String surename, String rule, String tell, String email, String tokenid, String uid, String password) {
        this.name = name;
        this.surename = surename;
        this.rule = rule;
        this.tell = tell;
        this.email = email;
        this.tokenid = tokenid;
        this.uid = uid;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurename() {
        return surename;
    }

    public void setSurename(String surename) {
        this.surename = surename;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public String getTell() {
        return tell;
    }

    public void setTell(String tell) {
        this.tell = tell;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTokenid() {
        return tokenid;
    }

    public void setTokenid(String tokenid) {
        this.tokenid = tokenid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
