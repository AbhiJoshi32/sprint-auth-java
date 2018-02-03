package com.binktec.auth.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@SuppressWarnings("ALL")
@Entity
@SequenceGenerator(name="seq", allocationSize=100)
@Table(name = "users")
public class Users implements Serializable {
    @Transient
    private static final long serialVersionUID = 1L;

    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private int id;
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

    @ManyToMany(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns =
    @JoinColumn(name="user_id"),inverseJoinColumns =
    @JoinColumn(name = "role_id"))
    private Set<Role> roles;

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

    public Users() {
    }

    Users(Users users) {
        this.active = users.active;
        this.email = users.email;
        this.password = users.password;
        this.isVerified = users.isVerified;
        this.id = users.id;
        this.username = users.username;
        this.name = users.name;
        this.roles = users.roles;
    }


    public Users(RegisterUserApi registerUserApi) {
        this.email = registerUserApi.getEmail();
        this.password = registerUserApi.getPassword();
        this.username = registerUserApi.getUsername();
        this.name = registerUserApi.getName();
        this.address = registerUserApi.getAddress();
        this.phone = registerUserApi.getPhone();
        this.setVerified(false);
        this.setActive(1);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
