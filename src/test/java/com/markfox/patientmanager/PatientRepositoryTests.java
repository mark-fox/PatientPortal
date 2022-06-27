package com.markfox.patientmanager;

import com.markfox.patientmanager.models.Doctor;
import com.markfox.patientmanager.models.Patient;
import com.markfox.patientmanager.repositories.DoctorRepository;
import com.markfox.patientmanager.repositories.PatientRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

public class PatientRepositoryTests {

    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private DoctorRepository doctorRepository;

    // Testing to save a Patient to database with Repository
    @Test
    @Order(1)
    public void savePatientTest() {
        Patient patient = new Patient();
        patient.setFirstName("Matt");
        patient.setLastName("Damon");
        patient.setDateOfBirth(LocalDate.now().minusYears(1));
        patient.setEmailAddress("test@test.com");
        patient.setDoc(new Doctor());

        Assertions.assertNull(patient.getId());
        Patient savedPatient = patientRepository.save(patient);
        Assertions.assertNotNull(savedPatient.getId());
        Assertions.assertNotNull(savedPatient);
    }

    // Testing to modify the Patient in database with Repository
    @Test
    @Order(2)
    public void updatePatientTest() {
        Patient patient = new Patient();
        patient.setFirstName("Matt");
        patient.setLastName("Damon");
        patient.setDateOfBirth(LocalDate.now().minusYears(1));
        patient.setEmailAddress("test@test.com");
        patient.setDoc(new Doctor());
        Patient savedPatient = patientRepository.save(patient);

        // Modifying values
        savedPatient.setFirstName("Andy");
        savedPatient.setLastName("Dandy");

        Patient updatedPatient = patientRepository.save(savedPatient);

        Assertions.assertNotEquals(savedPatient.getFirstName().equals(updatedPatient.getFirstName()), !savedPatient.getFirstName().equals(updatedPatient.getFirstName()));
        Assertions.assertNotEquals(savedPatient.getLastName().equals(updatedPatient.getLastName()), !savedPatient.getLastName().equals(updatedPatient.getLastName()));

        Assertions.assertEquals(updatedPatient.getId(), savedPatient.getId());
        Assertions.assertEquals(updatedPatient.getId(), savedPatient.getId());
    }

    // Testing to delete Patient from database
    @Test
    public void deletePatientTest() {
        Patient patient = new Patient();
        patient.setFirstName("Matt");
        patient.setLastName("Damon");
        patient.setDateOfBirth(LocalDate.now().minusYears(1));
        patient.setEmailAddress("test@test.com");
        patient.setDoc(new Doctor());
        Patient savedPatient = patientRepository.save(patient);

        Assertions.assertNotNull(savedPatient.getId());
        patientRepository.deleteById(savedPatient.getId());

        Assertions.assertEquals(Optional.empty(), patientRepository.findById(savedPatient.getId()));
    }

    // Testing custom query method that sets each Patient's Doctor to null
    // for when a Doctor is deleted from the database
    @Test
    public void updatePatientDocByDocIdTest() {
        Doctor doctor = new Doctor();
        doctor.setFirstName("Bob");
        doctor.setLastName("Barker");
        Doctor savedDoctor = doctorRepository.save(doctor);
        Long docId = savedDoctor.getDocId();

        Patient patient = new Patient();
        patient.setFirstName("Matt");
        patient.setLastName("Damon");
        patient.setDateOfBirth(LocalDate.now().minusYears(1));
        patient.setEmailAddress("test@test.com");
        patient.setDoc(savedDoctor);

        Patient savedPatient = patientRepository.save(patient);
        Assertions.assertNotNull(savedPatient.getDoc());

        Optional<List<Patient>> patients = Optional.ofNullable(doctorRepository.findById(docId).get().getDocsPatients());
        Assertions.assertNotNull(patients);
        patientRepository.updatePatientDocByDocId(docId);

        Optional<List<Patient>> patients2 = Optional.ofNullable(doctorRepository.findById(docId).get().getDocsPatients());
        Assertions.assertEquals(Optional.empty(), patients2);
    }
}
