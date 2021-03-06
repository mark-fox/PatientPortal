package com.markfox.patientmanager;

import com.markfox.patientmanager.models.Doctor;
import com.markfox.patientmanager.repositories.DoctorRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;
import java.util.OptionalLong;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DoctorRepositoryTests {
    @Autowired
    private DoctorRepository doctorRepository;

//    Testing to save a Doctor to database with Repository
    @Test
    public void saveDoctorTest() {
        Doctor doctor = new Doctor();
        doctor.setFirstName("Bob");
        doctor.setLastName("Barker");

        Assertions.assertNull(doctor.getDocId());
        Doctor savedDoctor = doctorRepository.save(doctor);

        Assertions.assertNotNull(savedDoctor.getDocId());
        Assertions.assertNotEquals(doctor.getDocId(), OptionalLong.of(savedDoctor.getDocId()));
    }

    // Testing to delete Doctor from database
    @Test
    public void deleteDoctorTest() {
        Doctor doctor = new Doctor();
        doctor.setFirstName("Bob");
        doctor.setLastName("Barker");
        Doctor savedDoctor = doctorRepository.save(doctor);
        doctorRepository.deleteById(savedDoctor.getDocId());
        Assertions.assertEquals(Optional.empty(), doctorRepository.findById(savedDoctor.getDocId()));
    }
}
