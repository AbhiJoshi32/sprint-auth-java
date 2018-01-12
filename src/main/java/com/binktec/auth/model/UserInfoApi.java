package com.binktec.auth.model;

public class UserInfoApi {
    private String email;
    private String username;
    private String password;
    private String name;
    private String phone;
    private String address;
    private int active;
    private Boolean isVerified;

    public UserInfoApi(Users users) {
        this.email = users.getEmail();
        this.username = users.getUsername();
        this.name = users.getName();
        this.address = users.getAddress();
        this.phone = users.getPhone();
        this.active = users.getActive();
        this.isVerified = users.getVerified();
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
}
