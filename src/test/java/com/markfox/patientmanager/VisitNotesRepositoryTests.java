package com.markfox.patientmanager;

import com.markfox.patientmanager.models.Doctor;
import com.markfox.patientmanager.models.Patient;
import com.markfox.patientmanager.models.VisitNotes;
import com.markfox.patientmanager.repositories.PatientRepository;
import com.markfox.patientmanager.repositories.VisitNotesRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class VisitNotesRepositoryTests {
    @Autowired
    private VisitNotesRepository visitNotesRepository;
    @Autowired
    private PatientRepository patientRepository;

    // Testing to save Visit Notes to database with Repository
    @Test
    public void saveVisitNotesTest() {
        VisitNotes note = new VisitNotes();
        String reason = "testing reason";
        String description = "testing description";
        LocalDate visitDate = LocalDate.now();

        // Patient Entity needed to attach Visit Notes to
        Patient patient = new Patient();
        patient.setFirstName("Matt");
        patient.setLastName("Damon");
        patient.setDateOfBirth(LocalDate.now().minusYears(1));
        patient.setDoc(new Doctor());
        Patient savedPatient = patientRepository.save(patient);

        note.setVisitReason(reason);
        note.setDescription(description);
        note.setVisitDate(visitDate);
        note.setVisitsPatient(savedPatient);

        VisitNotes savedNote = visitNotesRepository.save(note);
        Assertions.assertEquals(reason, savedNote.getVisitReason());
    }

    // Testing to delete Visit Notes from database
    @Test
    public void deleteVisitNotesTest() {
        VisitNotes note = new VisitNotes();
        String reason = "testing reason";
        String description = "testing description";
        LocalDate visitDate = LocalDate.now();

        // Patient Entity needed to attach Visit Notes to
        Patient patient = new Patient();
        patient.setFirstName("Matt");
        patient.setLastName("Damon");
        patient.setDateOfBirth(LocalDate.now().minusYears(1));
        patient.setDoc(new Doctor());
        Patient savedPatient = patientRepository.save(patient);

        note.setVisitReason(reason);
        note.setDescription(description);
        note.setVisitDate(visitDate);
        note.setVisitsPatient(savedPatient);

        VisitNotes savedNote = visitNotesRepository.save(note);
        Long id = savedNote.getId();
        visitNotesRepository.deleteById(id);
        Assertions.assertNotEquals(id, visitNotesRepository.findById(id));
    }
}
