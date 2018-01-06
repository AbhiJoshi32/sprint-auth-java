package com.binktec.auth.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

public class UserApi {
    private String email;
    private String username;
    private String password;
    private String name;
    private int active;
    private Boolean isVerified;
    private String phone;
    private String address;

    public UserApi(Users users) {
        this.active = users.getActive();
        this.email = users.getEmail();
        this.password = users.getPassword();
        this.isVerified = users.getVerified();
        this.username = users.getUsername();
        this.name = users.getName();
        this.address = users.getAddress();
        this.phone = users.getPhone();
    }

    public UserApi() {
    }

    public UserApi(String email, String username, String password, String name, int active) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.name = name;
        this.active = active;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public Boolean getVerified() {
        return isVerified;
    }

    public void setVerified(Boolean verified) {
        isVerified = verified;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
