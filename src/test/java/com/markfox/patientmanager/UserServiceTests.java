package com.markfox.patientmanager;

import com.markfox.patientmanager.exceptions.MyException;
import com.markfox.patientmanager.models.User;
import com.markfox.patientmanager.services.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTests {
    @Autowired
    UserService userService;

    // Tests adding a new User to the database
    @Test
    public void addNewUserTest() throws MyException {
        User user = new User();
        user.setEmail("test@test.com");
        user.setPassword("password");

        User savedUser = userService.addNewUser(user);
        Assertions.assertEquals(savedUser.getEmail(), user.getEmail());
    }
}
