package com.markfox.patientmanager;

import com.markfox.patientmanager.exceptions.MyException;
import com.markfox.patientmanager.models.Doctor;
import com.markfox.patientmanager.models.Patient;
import com.markfox.patientmanager.services.DoctorService;
import com.markfox.patientmanager.services.PatientService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
public class PatientServiceTests {
    @Autowired
    PatientService patientService;
    @Autowired
    DoctorService doctorService;

//    Tests retrieving Patient by their ID
    @Test
    public void getPatientByIdTest() throws MyException {
        Doctor doctor = new Doctor();
        doctor.setFirstName("Bob");
        doctor.setLastName("Barker");
        Doctor savedDoctor = doctorService.addDoctor(doctor);

        Patient patient = new Patient();
        patient.setFirstName("Matt");
        patient.setLastName("Damon");
        patient.setDateOfBirth(LocalDate.now().minusYears(1));
        patient.setEmailAddress("test@test.com");
        patient.setDoc(savedDoctor);
        Patient savedPatient = patientService.addPatient(patient);

        Patient foundPatient = patientService.getPatientById(savedPatient.getId());
        Assertions.assertEquals(foundPatient.getFirstName(), savedPatient.getFirstName());
    }
}
