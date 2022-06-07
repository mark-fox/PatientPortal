package com.markfox.patientmanager.services;

import com.markfox.patientmanager.models.User;

public interface UserService {
    User getUserByEmail(String email);
}
