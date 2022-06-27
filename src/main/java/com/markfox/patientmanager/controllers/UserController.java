package com.markfox.patientmanager.controllers;

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
        model.addAttribute("user", new User());
        return "registration";
    }

    // Return route for saving a new User to database
    @PostMapping("/registration")
    public String registerNewUser(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("errors", result.getAllErrors());
            return "registration";
        }
        userService.addNewUser(user);
        return "redirect:/dashboard";
    }
}
