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

// Controller for routes that mainly deals with the Doctor Entity class
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

    // Route for viewing an individual Doctor and their Patients
    @GetMapping("/doctors/{id}")
    public String viewDoctor(@PathVariable Long id, Model model) throws MyException {
        model.addAttribute("doctor", doctorService.getDoctorById(id));
        // Retrieves list of Patients that are mapped to this Doctor
        model.addAttribute("patients", doctorService.getAllDocsPatients(id));
        return "viewdoctor";
    }

    // Route for adding a new Doctor
    @GetMapping("/doctors/newdoctor")
    public String viewNewDoctor(Model model) {
        // Creates a Doctor instance to hold field values
        Doctor doctor = new Doctor();
        model.addAttribute("doctor", doctor);
        return "newdoctor";
    }

    // Return route to save a new Doctor to database
    @PostMapping("/doctors/newdoctor")
    public String addNewDoctor(@Valid @ModelAttribute("doctor") Doctor doctor, BindingResult result, Model model) throws MyException {
        // Checks for errors that are defined in the Doctor class
        if (result.hasErrors()) {
            // Sends user back to the same page they were on with list of errors to correct
            model.addAttribute("errors", result.getAllErrors());
            return "newdoctor";
        }
        // Saves the new Doctor to database
        doctorService.addDoctor(doctor);
        // Sends the user back to the main Doctor page
        return "redirect:/doctors";
    }

    // Route to delete an individual Doctor based on the provided ID
    @GetMapping("/doctors/delete/{id}")
    public String deleteDoctor(@PathVariable Long id) throws MyException {
        // First the mapped Patients' Doctor attribute is set to NULL
        patientService.removePatientsDocByDocId(id);
        // Deletes the specified Doctor from the database
        doctorService.deleteDoctorById(id);
        // Sends the user back to the main Doctor page
        return "redirect:/doctors";
    }
}
