package com.markfox.patientmanager;

import com.markfox.patientmanager.models.Doctor;
import com.markfox.patientmanager.models.Patient;
import com.markfox.patientmanager.repositories.PatientRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

public class PatientRepositoryTests {

    @Autowired
    private PatientRepository patientRepository;


    @Test
    public void savePatientTest() {
        Patient patient = new Patient();
        patient.setFirstName("Matt");
        patient.setLastName("Damon");
        patient.setDoc(new Doctor());

        Assertions.assertNull(patient.getId());
        Patient savedPatient = patientRepository.save(patient);
        Assertions.assertNotNull(savedPatient.getId());
        Assertions.assertNotNull(savedPatient);
    }

    @Test
    public void updatePatientTest() {
        Patient patient = new Patient();
        patient.setFirstName("Matt");
        patient.setLastName("Damon");
        patient.setDoc(new Doctor());
        Patient savedPatient = patientRepository.save(patient);

        savedPatient.setFirstName("Andy");
        savedPatient.setLastName("Dandy");

        Patient updatedPatient = patientRepository.save(savedPatient);

        Assertions.assertNotEquals(savedPatient.getFirstName().equals(updatedPatient.getFirstName()), !savedPatient.getFirstName().equals(updatedPatient.getFirstName()));
        Assertions.assertNotEquals(savedPatient.getLastName().equals(updatedPatient.getLastName()), !savedPatient.getLastName().equals(updatedPatient.getLastName()));

        Assertions.assertEquals(updatedPatient.getId(), savedPatient.getId());
        Assertions.assertEquals(updatedPatient.getId(), savedPatient.getId());

    }

    @Test
    public void deletePatientTest() {
        Patient patient = new Patient();
        patient.setFirstName("Matt");
        patient.setLastName("Damon");
        patient.setDoc(new Doctor());
        Patient savedPatient = patientRepository.save(patient);

        Assertions.assertNotNull(savedPatient.getId());
        patientRepository.deleteById(savedPatient.getId());

        Assertions.assertEquals(Optional.empty(), patientRepository.findById(savedPatient.getId()));
    }
}
