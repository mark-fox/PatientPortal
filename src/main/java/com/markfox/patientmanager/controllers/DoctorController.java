package com.markfox.patientmanager.controllers;

import com.markfox.patientmanager.exceptions.MyException;
import com.markfox.patientmanager.models.Doctor;
import com.markfox.patientmanager.services.DoctorService;
import com.markfox.patientmanager.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class DoctorController {
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private PatientService patientService;


    // Route to list of all Doctors
    @GetMapping("/doctors")
    public String viewAllDoctors(Model model) {
        model.addAttribute("doctors", doctorService.getAllDoctors());
        return "doctors";
    }

    // Route to viewing an individual Doctor and their Patients
    @GetMapping("/doctors/{id}")
    public String viewDoctor(@PathVariable Long id, Model model) throws MyException {
        model.addAttribute("doctor", doctorService.getDoctorById(id));
        model.addAttribute("patients", doctorService.getAllDocsPatients(id));
        return "viewdoctor";
    }

    // Route for adding a new Doctor
    @GetMapping("/doctors/newdoctor")
    public String viewNewDoctor(Model model) {
        Doctor doctor = new Doctor();
        model.addAttribute("doctor", doctor);
        return "newdoctor";
    }

    // Return route to save a new Doctor to database
    @PostMapping("/doctors/newdoctor")
    public String addNewDoctor(@Valid @ModelAttribute("doctor") Doctor doctor, BindingResult result, Model model) throws MyException {
        if (result.hasErrors()) {
            model.addAttribute("errors", result.getAllErrors());
            return "newdoctor";
        }
        doctorService.addDoctor(doctor);
        return "redirect:/doctors";
    }

    // Route to delete an individual Doctor based on the provided ID
    @GetMapping("/doctors/delete/{id}")
    public String deleteDoctor(@PathVariable Long id) throws MyException {
        patientService.removePatientsDocByDocId(id);
        doctorService.deleteDoctorById(id);
        return "redirect:/doctors";
    }
}
