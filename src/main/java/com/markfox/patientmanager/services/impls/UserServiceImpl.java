package com.markfox.patientmanager.services.impls;

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

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    BCryptPasswordEncoder myPasswordEncoder;

    public void setMyPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.myPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(username);
        System.out.println("was a user retrieved: " + user);
        // Exception handling checking if User exists
        user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + username));

        return user.map(MyUserDetails::new).get();
    }

    @Override
    public User addNewUser(User user) {
        if (myPasswordEncoder == null) {
            setMyPasswordEncoder(new BCryptPasswordEncoder());
        }
        user.setPassword(myPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}
