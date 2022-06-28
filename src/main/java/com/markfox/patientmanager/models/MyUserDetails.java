package com.markfox.patientmanager.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

// Custom implementation of the UserDetails interface
public class MyUserDetails implements UserDetails {

    private String email;
    private String password;
    private boolean active;
    // Authorities (Roles) are included for future implementation, but
    // do not serve any purpose at this time
    private List<GrantedAuthority> authorities;
    private User myUser;

    // Constructor
    public MyUserDetails(User user) {
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.active = user.isActive();
        this.authorities = Arrays.stream(user.getRoles().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        this.myUser = user;
    }

    // Overridden required methods
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    // Set to true as a default for now
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // Set to true as a default for now
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // Set to true as a default for now
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }

    // Getters and Setters
    public User getMyUser() {
        return myUser;
    }

    public void setMyUser(User myUser) {
        this.myUser = myUser;
    }
}
