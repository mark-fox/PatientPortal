package com.markfox.patientmanager.controllers;

import com.markfox.patientmanager.services.DoctorService;
import com.markfox.patientmanager.services.PatientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class DoctorController {
    private DoctorService doctorService;
    private PatientService patientService;

    public DoctorController(DoctorService doctorService, PatientService patientService) {
        this.doctorService = doctorService;
        this.patientService = patientService;
    }

    @GetMapping("/doctors")
    public String viewAllDoctors(Model model) {
        model.addAttribute("doctors", doctorService.getAllDoctors());
        return "doctors";
    }

    @GetMapping("/doctors/{id}")
    public String viewDoctor(@PathVariable Long id, Model model) {
        model.addAttribute("doctor", doctorService.getDoctorById(id));
        // model.addAttribute("patients", doctorService.getAllDocsPatients());
        return "viewdoctor";
    }
}
