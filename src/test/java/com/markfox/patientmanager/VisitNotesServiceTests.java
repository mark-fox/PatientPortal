package com.markfox.patientmanager;

import com.markfox.patientmanager.exceptions.MyException;
import com.markfox.patientmanager.models.Doctor;
import com.markfox.patientmanager.models.Patient;
import com.markfox.patientmanager.models.VisitNotes;
import com.markfox.patientmanager.services.DoctorService;
import com.markfox.patientmanager.services.PatientService;
import com.markfox.patientmanager.services.VisitNotesService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
public class VisitNotesServiceTests {
    @Autowired
    VisitNotesService visitNotesService;
    @Autowired
    PatientService patientService;
    @Autowired
    DoctorService doctorService;

    // Tests adding new Visit Notes to database
    @Test
    public void addVisitNotesTest() throws MyException {
        // Doctor sometimes needed to attach to Patient
        Doctor doctor = new Doctor();
        doctor.setFirstName("Bob");
        doctor.setLastName("Barker");
        Doctor savedDoctor = doctorService.addDoctor(doctor);

        // Patient needed to attach Visit Notes to
        Patient patient = new Patient();
        patient.setFirstName("Matt");
        patient.setLastName("Damon");
        patient.setDateOfBirth(LocalDate.now().minusYears(1));
        patient.setEmailAddress("test@test.com");
        patient.setDoc(savedDoctor);
        patientService.addPatient(patient);

        VisitNotes notes = new VisitNotes();
        notes.setVisitReason("reason");
        notes.setVisitDate(LocalDate.of(2001,1,1));
        notes.setDescription("description");
        notes.setVisitsPatient(patient);

        VisitNotes savedUser = visitNotesService.addVisitNotes(notes);
        Assertions.assertEquals(savedUser.getVisitReason(), notes.getVisitReason());
    }
}
