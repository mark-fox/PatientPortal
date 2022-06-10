package com.markfox.patientmanager.controllers;

import com.markfox.patientmanager.models.Doctor;
import com.markfox.patientmanager.models.Patient;
import com.markfox.patientmanager.services.DoctorService;
import com.markfox.patientmanager.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DoctorController {
    @Autowired
    private DoctorService doctorService;
//    @Autowired
//    private PatientService patientService;



    @GetMapping("/doctors")
    public String viewAllDoctors(Model model) {
        model.addAttribute("doctors", doctorService.getAllDoctors());
        return "doctors";
    }

    @GetMapping("/doctors/{id}")
    public String viewDoctor(@PathVariable Long id, Model model) {
        model.addAttribute("doctor", doctorService.getDoctorById(id));
        model.addAttribute("patients", doctorService.getAllDocsPatients(id));
        return "viewdoctor";
    }

    @GetMapping("/doctors/newdoctor")
    public String viewNewDoctor(Model model) {
        Doctor doctor = new Doctor();
        model.addAttribute("doctor", doctor);
        return "newdoctor";
    }
    @PostMapping("/doctors/newdoctor")
    public String addNewDoctor(@ModelAttribute("doctor") Doctor doctor) {
        doctorService.addDoctor(doctor);
        return "redirect:/doctors";
    }
}
