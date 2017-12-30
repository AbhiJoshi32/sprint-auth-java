package com.binktec.auth.model;

import javax.persistence.Column;

public class UserApi {
    @Column(name = "email")
    private String email;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "name")
    private String name;
    @Column(name = "is_active")
    private int active;
    @Column(name = "is_verified")
    private Boolean isVerified;
    @Column(name="phone")
    private String phone;
    @Column(name="address")
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
