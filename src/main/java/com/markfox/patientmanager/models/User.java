package com.markfox.patientmanager.models;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Objects;

// Entity class for Users and the associated database table
@Entity
@Table(name = "users")
public class User {
    // Primary Key
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // Required username/email attribute that must be unique in database
    @Column (name="email", nullable=false, unique = true)
    @Email(message = "Must enter a valid email address")
    private String email;

    // Simple data validation added to password attribute
    // Setting Max is for the encrypted value's length
    @Column(name = "password", nullable = false)
    @Size(min=4, message="Password must be at least 4 characters")
    private String password;

    // Default values assigned to these attributes for now, but
    // could be implemented in future versions
    private boolean active = true;
    private String roles = "USER";


    // Overridden methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return active == user.active && Objects.equals(id, user.id) && Objects.equals(email, user.email) && Objects.equals(password, user.password) && Objects.equals(roles, user.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, password, active, roles);
    }


    // Getters and Setters
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
