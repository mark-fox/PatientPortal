package com.markfox.patientmanager.services.impls;

import com.markfox.patientmanager.exceptions.MyException;
import com.markfox.patientmanager.models.MyUserDetails;
import com.markfox.patientmanager.models.User;
import com.markfox.patientmanager.repositories.UserRepository;
import com.markfox.patientmanager.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

// Service class for User entity and UserRepository
// Implements the UserService interface for use with UserDetails
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    BCryptPasswordEncoder myPasswordEncoder;

    // Setter for Password Encoder
    // Challenge: Users were not being retrieved when this was set up with
    // either Autowired or a Constructor (dependency injection)
    public void setMyPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.myPasswordEncoder = bCryptPasswordEncoder;
    }

    // Overridden method for retrieving User from database
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(username);
        // Exception handling checking if User exists
        user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + username));
        return user.map(MyUserDetails::new).get();
    }

    // Method for saving a new User to database
    @Override
    public User addNewUser(User user) throws MyException {
        if (user == null) {
            throw new MyException("Error: User object cannot be null");
        }
        // Checks that the password encoder is instantiated
        if (myPasswordEncoder == null) {
            setMyPasswordEncoder(new BCryptPasswordEncoder());
        }
        // Encodes the password before saving to database
        user.setPassword(myPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    // Method for changing the User's password
    @Override
    public void updateUserPassword(String newPassword, String email) {
        // Checks that the password encoder is instantiated
        if (myPasswordEncoder == null) {
            setMyPasswordEncoder(new BCryptPasswordEncoder());
        }
        userRepository.updateUserPasswordByEmail(myPasswordEncoder.encode(newPassword), email);
    }
}
