package com.markfox.patientmanager.controllers;

import com.markfox.patientmanager.models.VisitNotes;
import com.markfox.patientmanager.services.PatientService;
import com.markfox.patientmanager.services.VisitNotesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;

@Controller
public class VisitNotesController {
    private final VisitNotesService visitNotesService;
    private final PatientService patientService;

    // Constructor for dependency injections of Service classes
    public VisitNotesController(VisitNotesService visitNotesService, PatientService patientService) {
        this.visitNotesService = visitNotesService;
        this.patientService = patientService;
    }

    // Route to add a new Visit Note
    @GetMapping("/newnotes/{id}")
    public String viewNewVisitNotes(@PathVariable Long id, Model model) {
        VisitNotes visitNotes = new VisitNotes();
        model.addAttribute("visit", visitNotes);
        model.addAttribute("patient", patientService.getPatientById(id));
        model.addAttribute("currDate", LocalDate.now());
        return "newvisitnotes";
    }

    // Return route to save a new Visit Note to database
    @PostMapping("/newnotes/{id}")
    public String addNewVisitNotes(@PathVariable Long id,
                                   @ModelAttribute("visit") VisitNotes visitNotes) {
        visitNotes.setVisitsPatient(patientService.getPatientById(id));
        visitNotes.setVisitDate(visitNotes.getVisitDate());
        visitNotesService.addVisitNotes(visitNotes);
        return "redirect:/dashboard/{id}";
    }

    // Route to delete an individual Visit Note
    @PostMapping("/deletenote/{id}")
    public String deleteVisitNotes(@PathVariable Long id) {
        visitNotesService.deleteVisitNoteById(id);
        return "redirect:/dashboard";
    }
}
