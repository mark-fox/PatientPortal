package com.markfox.patientmanager;

import com.markfox.patientmanager.exceptions.MyException;
import com.markfox.patientmanager.models.Doctor;
import com.markfox.patientmanager.repositories.DoctorRepository;
import com.markfox.patientmanager.services.DoctorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DoctorServiceTests {

    @Autowired
    DoctorService doctorService;
    @Autowired
    DoctorRepository doctorRepository;

//    Tests retrieving a Doctor by its ID
    @Test
    public void getDoctorByIdTest() throws MyException {
        Doctor doctor = new Doctor();
        doctor.setFirstName("Bob");
        doctor.setLastName("Barker");
        Doctor savedDoctor = doctorRepository.save(doctor);

        Doctor foundDoctor = doctorService.getDoctorById(savedDoctor.getDocId());
        Assertions.assertEquals(foundDoctor.getFirstName(), savedDoctor.getFirstName());
    }

    // Parameterized Test that creates Doctor objects based off the argument
    // and saves to the database
    @ParameterizedTest
    @ValueSource(strings = {"FirstName1;LastName1", "FirstName2;LastName2"})
    public void addingNewDoctorsTest(String args) throws MyException {
        String[] names = args.split(";");
        Doctor doctor = new Doctor(names[0], names[1]);
        Doctor savedDoctor = doctorService.addDoctor(doctor);
        // Tests that the firstName field matches
        Assertions.assertEquals(doctor.getFirstName().equals(savedDoctor.getFirstName()),
                                savedDoctor.getFirstName().equals(names[0]));
    }
}
