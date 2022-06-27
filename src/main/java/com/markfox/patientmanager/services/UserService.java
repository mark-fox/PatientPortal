package com.markfox.patientmanager.services;

import com.markfox.patientmanager.exceptions.MyException;
import com.markfox.patientmanager.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;

// User DAO class
public interface UserService extends UserDetailsService {
    User addNewUser(User user) throws MyException;
    void updateUserPassword(String newPassword, String email);
}
