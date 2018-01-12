package com.binktec.auth.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

public class RegisterUserApi {
    private String email;
    private String username;
    private String password;
    private String name;
    private String phone;
    private String address;

    public RegisterUserApi(Users users) {
        this.email = users.getEmail();
        this.password = users.getPassword();
        this.username = users.getUsername();
        this.name = users.getName();
        this.address = users.getAddress();
        this.phone = users.getPhone();
    }

    public RegisterUserApi() {
    }

    public RegisterUserApi(String email, String username, String password, String name) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.name = name;
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
