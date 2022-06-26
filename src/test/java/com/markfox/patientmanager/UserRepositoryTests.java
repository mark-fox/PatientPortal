package com.markfox.patientmanager;

import com.markfox.patientmanager.models.User;
import com.markfox.patientmanager.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTests {

    @Autowired
    private UserRepository userRepository;

    // Testing to save User to database with Repository
    @Test
    public void saveUserTest() {
        User user = new User();
        String email = "test@testemail.com";
        String password = "password";
        user.setEmail(email);
        user.setPassword(password);

        User savedUser = userRepository.save(user);
        Assertions.assertEquals(email, savedUser.getEmail());
    }

    // Testing to delete User from database
    @Test
    public void deleteUserTest() {
        User user = new User();
        String email = "test@testemail.com";
        String password = "password";
        user.setEmail(email);
        user.setPassword(password);

        User savedUser = userRepository.save(user);
        Assertions.assertNotNull(savedUser);

        userRepository.delete(savedUser);
        Optional<User> deletedUser = userRepository.findById(savedUser.getId());
        Assertions.assertEquals(Optional.empty(), deletedUser);

    }

    // Testing custom query method used during Authentication
    @Test
    public void findByEmailTest() {
        User user = new User();
        String email = "test@testemail.com";
        String password = "password";
        user.setEmail(email);
        user.setPassword(password);

        User savedUser = userRepository.save(user);
        Optional<User> searchedUser = userRepository.findByEmail(email);
        Assertions.assertEquals(email, searchedUser.get().getEmail());
    }
}
