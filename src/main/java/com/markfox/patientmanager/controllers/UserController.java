package com.markfox.patientmanager.controllers;

import com.markfox.patientmanager.exceptions.MyException;
import com.markfox.patientmanager.models.User;
import com.markfox.patientmanager.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

// Main Controller that handles the homepage, registration, and logging in
@Controller
public class UserController {
    @Autowired
    private UserService userService;

    // Main routes that direct to Login page
    @GetMapping("/")
    public String homePage() {
        return "login";
    }
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    // Route to Registration page for creating new User
    @GetMapping("/registration")
    public String showRegistration(Model model) {
        // Creates User instance to hold field values
        model.addAttribute("user", new User());
        return "registration";
    }

    // Return route for saving a new User to database
    @PostMapping("/registration")
    public String registerNewUser(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) throws MyException {
        // Checks for errors that are defined in the User class
        if (result.hasErrors()) {
            // Returns the user back to the same page they were on with list of errors
            model.addAttribute("errors", result.getAllErrors());
            return "registration";
        }
        // Saves the new User to the database
        userService.addNewUser(user);
        // Sends the user back to the Login page to log into the website
        return "redirect:/?registered";
    }
}
