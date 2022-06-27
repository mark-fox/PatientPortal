package com.markfox.patientmanager;

import com.markfox.patientmanager.exceptions.MyException;
import com.markfox.patientmanager.models.Doctor;
import com.markfox.patientmanager.repositories.DoctorRepository;
import com.markfox.patientmanager.services.DoctorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DoctorServiceTests {

    @Autowired
    DoctorService doctorService;
    @Autowired
    DoctorRepository doctorRepository;

    @Test
    public void getDoctorByIdTest() throws MyException {
        Doctor doctor = new Doctor();
        doctor.setFirstName("Bob");
        doctor.setLastName("Barker");
        Doctor savedDoctor = doctorRepository.save(doctor);

        Doctor foundDoctor = doctorService.getDoctorById(savedDoctor.getDocId());
        Assertions.assertEquals(foundDoctor.getFirstName(), savedDoctor.getFirstName());
    }
}
