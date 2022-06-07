package com.markfox.patientmanager.controllers;

import com.markfox.patientmanager.models.Patient;
import com.markfox.patientmanager.services.PatientService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PatientController {
    private PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }


    @GetMapping("/login")
    public String viewLoginPage() {
        return "login";
    }

    @GetMapping("/dashboard")
    public String viewAllPatients(Model model) {
        model.addAttribute("patients", patientService.getAllPatients());
        return "dashboard";
    }

    @GetMapping("/dashboard/newpatient")
    public String viewNewPatient(Model model) {
        Patient patient = new Patient();
        model.addAttribute("patient", patient);
        return "newpatient";
    }
    @PostMapping("/dashboard/newpatient")
    public String addNewPatient(@ModelAttribute("patient") Patient patient) {
        patientService.addPatient(patient);
        return "redirect:/dashboard";
    }

    @GetMapping("/dashboard/{id}")
    public String viewPatient(@PathVariable Long id, Model model) {
        model.addAttribute("patient", patientService.getPatientById(id));
        return "viewpatient";
    }
    @PostMapping("/dashboard/{id}")
    public String editPatient(@PathVariable Long id, @ModelAttribute("patient") Patient patient, Model model) {
        Patient savedPatient = patientService.getPatientById(id);
        savedPatient.setFirstName(patient.getFirstName());
        savedPatient.setLastName(patient.getLastName());
        savedPatient.setDoctor(patient.getDoctor());

        patientService.updatePatient(savedPatient);
        return "redirect:/dashboard";
    }

    // onetomany attempt
//    @GetMapping("/testing/{docId}")
//    public Page<Patient> testingattempt(@PathVariable(value="docId") Long docId, Pageable pageable) {
//        return patientService.getPatientByDocId(docId, pageable);
//    }
}
