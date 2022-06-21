package com.markfox.patientmanager.services;

import com.markfox.patientmanager.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;


public interface UserService extends UserDetailsService {
    User addNewUser(User user);
}
