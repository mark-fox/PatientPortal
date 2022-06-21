package com.markfox.patientmanager.models;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column (name="email", nullable=false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    private boolean active = true;
    private String roles = "USER";

//    public User() {
//
//    }
//
//    public User(String email, String password, boolean active, String roles) {
//        this.email = email;
//        this.password = password;
//        this.active = active;
//        this.roles = roles;
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }
}
