package com.markfox.patientmanager.controllers;

import com.markfox.patientmanager.exceptions.MyException;
import com.markfox.patientmanager.models.Patient;
import com.markfox.patientmanager.services.DoctorService;
import com.markfox.patientmanager.services.PatientService;
import com.markfox.patientmanager.services.VisitNotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;

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
        Patient patient = new Patient();
        model.addAttribute("patient", patient);
        model.addAttribute("doctors", doctorService.getAllDoctors());
        // Dates used for part of data restriction
        model.addAttribute("currDate", LocalDate.now());
        model.addAttribute("limitDate", LocalDate.now().minusYears(150));
        return "newpatient";
    }

    // Return route for saving a new Patient to database
    @PostMapping("/dashboard/newpatient")
    public String addNewPatient(@Valid @ModelAttribute("patient") Patient patient, BindingResult result, Model model) throws MyException {
        if (result.hasErrors()) {
            model.addAttribute("errors", result.getAllErrors());
            model.addAttribute("doctors", doctorService.getAllDoctors());
            // Dates used for part of data restriction
            model.addAttribute("currDate", LocalDate.now());
            model.addAttribute("limitDate", LocalDate.now().minusYears(150));
            return "newpatient";
        }
        // Returned Doctor object only contains their ID
        patient.setDoc(doctorService.getDoctorById(patient.getDoc().getDocId()));
        patientService.addPatient(patient);
        return "redirect:/dashboard";
    }

    // Route to view an individual Patient
    @GetMapping("/dashboard/{id}")
    public String viewPatient(@PathVariable Long id, Model model) {
        model.addAttribute("patient", patientService.getPatientById(id));
        model.addAttribute("doctors", doctorService.getAllDoctors());
        model.addAttribute("visits", patientService.getAllVisitNotes(id));
        // Dates used for part of data restriction
        model.addAttribute("currDate", LocalDate.now());
        model.addAttribute("limitDate", LocalDate.now().minusYears(150));
        return "viewpatient";
    }

    // Return route for updating an individual Patient
    @PostMapping("/dashboard/{id}")
    public String editPatient(@PathVariable Long id, @Valid @ModelAttribute("patient") Patient patient, BindingResult result, Model model) throws MyException {
        if (result.hasErrors()) {
            model.addAttribute("errors", result.getAllErrors());
            model.addAttribute("doctors", doctorService.getAllDoctors());
            model.addAttribute("visits", patientService.getAllVisitNotes(id));
            // Dates used for part of data restriction
            model.addAttribute("currDate", LocalDate.now());
            model.addAttribute("limitDate", LocalDate.now().minusYears(150));
            return "viewpatient";
        }
        Patient savedPatient = patientService.getPatientById(id);
        savedPatient.setFirstName(patient.getFirstName());
        savedPatient.setLastName(patient.getLastName());
        savedPatient.setDateOfBirth(patient.getDateOfBirth());
        savedPatient.setGender(patient.getGender());
        savedPatient.setPhoneNumber(patient.getPhoneNumber());
        savedPatient.setEmailAddress(patient.getEmailAddress());
        savedPatient.setLastVisitDate(patient.getLastVisitDate());
        savedPatient.setRaceEthnicity(patient.getRaceEthnicity());
        savedPatient.setDoc(doctorService.getDoctorById(patient.getDoc().getDocId()));
        patientService.updatePatient(savedPatient);
        return "redirect:/dashboard";
    }

    // Route to delete an individual Patient
    @PostMapping(value="/dashboard/{id}", params = "delPatient")
    public String deletePatient(@PathVariable Long id) {
        patientService.deletePatientById(id);
        return "redirect:/dashboard";
    }
}
