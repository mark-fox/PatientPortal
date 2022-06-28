package com.markfox.patientmanager.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// Simple Controller for directing to custom error page
@Controller
public class MyErrorController implements ErrorController {

    // Route for custom error page
    @RequestMapping("/error")
    public String handleError() {
        return "error";
    }
}
