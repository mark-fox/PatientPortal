package com.markfox.patientmanager.services.impls;

import com.markfox.patientmanager.exceptions.MyException;
import com.markfox.patientmanager.models.Doctor;
import com.markfox.patientmanager.models.Patient;
import com.markfox.patientmanager.repositories.DoctorRepository;
import com.markfox.patientmanager.services.DoctorService;
import org.springframework.stereotype.Service;

import java.util.List;

// Service class for Doctor entity and DoctorRepository
@Service
public class DoctorServiceImpl implements DoctorService {
    private final DoctorRepository doctorRepository;

    // Constructor for dependency injection of Repository
    public DoctorServiceImpl(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    // Retrieves all Doctors in database to be displayed in table or dropdown menu
    @Override
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    // Retrieves the specified Doctor by their ID if they exist
    @Override
    public Doctor getDoctorById(Long id) throws MyException {
        if (id == null || id < 1) {
            throw new MyException("Error: Doctor ID cannot be null");
        }
        return doctorRepository.findById(id).isPresent() ? doctorRepository.findById(id).get() : null;
    }

    // Queries for List of Patients with the specified Doctor, which is a mapped attribute
    @Override
    public List<Patient> getAllDocsPatients(Long id) throws MyException {
        if (id == null || id < 1) {
            throw new MyException("Error: Doctor ID cannot be null");
        }
        return doctorRepository.findById(id).isPresent() ? doctorRepository.findById(id).get().getDocsPatients() : null;
    }

    // Adds the provided Doctor to database
    @Override
    public Doctor addDoctor(Doctor doctor) throws MyException {
        if (doctor == null) {
            throw new MyException("Error: Doctor object cannot be null");
        }
        return doctorRepository.save(doctor);
    }

    // Deletes the specified Doctor from database
    @Override
    public void deleteDoctorById(Long id) throws MyException {
        if (id == null || id < 1) {
            throw new MyException("Error: Doctor ID cannot be null");
        }
        doctorRepository.deleteById(id);
    }
}
