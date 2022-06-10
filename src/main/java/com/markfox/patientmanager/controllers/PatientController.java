package com.markfox.patientmanager.controllers;

import com.markfox.patientmanager.models.Doctor;
import com.markfox.patientmanager.models.Patient;
import com.markfox.patientmanager.services.DoctorService;
import com.markfox.patientmanager.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PatientController {
    private PatientService patientService;
    @Autowired
    private DoctorService doctorService;

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
        model.addAttribute("doctors", doctorService.getAllDoctors());
//        System.out.println(doctorService.getAllDoctors());
        return "newpatient";
    }
    @PostMapping("/dashboard/newpatient")
    public String addNewPatient(@ModelAttribute("patient") Patient patient, Model model) { //  @RequestParam Doctor doc,
//        System.out.println(patient.getId());
//        System.out.println(patient.getFirstName());
//        System.out.println(patient.getLastName());
//        System.out.println(patient.getDoc().getDocId());
//        System.out.println(patient.getDoc().getFirstName());
//        System.out.println(patient.getDoc().getDocsPatients());
//        System.out.println(model);
//        System.out.println(model.getAttribute("doctorId"));
//        System.out.println(model.getAttribute("doctorid"));
//        Doctor testdoc = doctorService.getDoctorById(1L); // new Doctor("rick", "sanchez");
//        patient.setDoc(testdoc);
//        Patient test = new Patient("abc", "dfe", "mydoc", testdoc);
//        patientService.addPatient(test);

        patient.setDoc(doctorService.getDoctorById(patient.getDoc().getDocId()));
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
//    @GetMapping("/testing/{docId}")
//    public String testingattempt(@PathVariable(value="docId") Long docId, Model model) {
//        model.addAttribute("patients", doctorService.getAllDocsPatients(docId));
//        return "redirect:viewdoctor";
////        return patientService.getPatientsByDocId(docId);
//    }
}
