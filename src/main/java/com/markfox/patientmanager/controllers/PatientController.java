package com.markfox.patientmanager.controllers;

import com.markfox.patientmanager.exceptions.MyException;
import com.markfox.patientmanager.models.Patient;
import com.markfox.patientmanager.services.DoctorService;
import com.markfox.patientmanager.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;

// Controller for routes that mainly deals with the Patient Entity class
@Controller
public class PatientController {
    private final PatientService patientService;
    @Autowired
    private DoctorService doctorService;

    // Constructor for dependency injections of Service classes
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    // Route to list of all Patients
    @GetMapping("/dashboard")
    public String viewAllPatients(Model model) {
        model.addAttribute("patients", patientService.getAllPatients());
        return "dashboard";
    }

    // Route for adding a new Patient
    @GetMapping("/dashboard/newpatient")
    public String viewNewPatient(Model model) {
        // Creates Patient instance to hold field values
        Patient patient = new Patient();
        model.addAttribute("patient", patient);
        // All Doctors are sent to a dropdown menu for selection
        model.addAttribute("doctors", doctorService.getAllDoctors());
        // Dates used for part of data restriction
        model.addAttribute("currDate", LocalDate.now());
        model.addAttribute("limitDate", LocalDate.now().minusYears(150));
        return "newpatient";
    }

    // Return route for saving a new Patient to database
    @PostMapping("/dashboard/newpatient")
    public String addNewPatient(@Valid @ModelAttribute("patient") Patient patient, BindingResult result, Model model) throws MyException {
        // Checks for data validation errors defined in Patient class
        if (result.hasErrors()) {
            // Returns the errors and list of Doctors to repopulate dropdown menu
            model.addAttribute("errors", result.getAllErrors());
            model.addAttribute("doctors", doctorService.getAllDoctors());
            // Dates used for part of data restriction
            model.addAttribute("currDate", LocalDate.now());
            model.addAttribute("limitDate", LocalDate.now().minusYears(150));
            // Returns the user back to the same page they were on
            return "newpatient";
        }
        // Returned Doctor object only contains their ID
        patient.setDoc(doctorService.getDoctorById(patient.getDoc().getDocId()));
        // Saves new Patient to database
        patientService.addPatient(patient);
        // Sends user back to the main page with the new Patient added to the table
        return "redirect:/dashboard";
    }

    // Route to view an individual Patient
    @GetMapping("/dashboard/{id}")
    public String viewPatient(@PathVariable Long id, Model model) throws MyException {
        // Retrieves the Patient matching the passed ID
        model.addAttribute("patient", patientService.getPatientById(id));
        // All Doctors are sent to a dropdown menu for selection if editing the current Patient
        model.addAttribute("doctors", doctorService.getAllDoctors());
        // Retrieves all associated Visit Notes for the Patient
        model.addAttribute("visits", patientService.getAllVisitNotes(id));
        // Dates used for part of data restriction
        model.addAttribute("currDate", LocalDate.now());
        model.addAttribute("limitDate", LocalDate.now().minusYears(150));
        return "viewpatient";
    }

    // Return route for updating an individual Patient
    @PostMapping("/dashboard/{id}")
    public String editPatient(@PathVariable Long id, @Valid @ModelAttribute("patient") Patient patient, BindingResult result, Model model) throws MyException {
        // Checks for data validation errors defined in Patient class
        if (result.hasErrors()) {
            // Returns the errors, list of Doctors, and Visit Notes to repopulate fields
            model.addAttribute("errors", result.getAllErrors());
            model.addAttribute("doctors", doctorService.getAllDoctors());
            model.addAttribute("visits", patientService.getAllVisitNotes(id));
            // Dates used for part of data restriction
            model.addAttribute("currDate", LocalDate.now());
            model.addAttribute("limitDate", LocalDate.now().minusYears(150));
            // Returns the user back to the same page they were on
            return "viewpatient";
        }
        // Retrieves the Patient from the database so the same entry is used
        Patient savedPatient = patientService.getPatientById(id);
        // Updates each attribute with field data
        savedPatient.setFirstName(patient.getFirstName());
        savedPatient.setLastName(patient.getLastName());
        savedPatient.setDateOfBirth(patient.getDateOfBirth());
        savedPatient.setGender(patient.getGender());
        savedPatient.setPhoneNumber(patient.getPhoneNumber());
        savedPatient.setEmailAddress(patient.getEmailAddress());
        savedPatient.setLastVisitDate(patient.getLastVisitDate());
        savedPatient.setRaceEthnicity(patient.getRaceEthnicity());
        savedPatient.setDoc(doctorService.getDoctorById(patient.getDoc().getDocId()));
        // Updates the database with changes
        patientService.updatePatient(savedPatient);
        // Sends user back to the main page with an updated list of Patients
        return "redirect:/dashboard";
    }

    // Route to delete an individual Patient
    // The indicated param refers to the name attribute of the Delete button
    @PostMapping(value="/dashboard/{id}", params = "delPatient")
    public String deletePatient(@PathVariable Long id) throws MyException {
        // Deletes the specified Patient from the database
        patientService.deletePatientById(id);
        // Sends user back to the main page with an updated list of Patients
        return "redirect:/dashboard";
    }
}
