package com.markfox.patientmanager.services.impls;

import com.markfox.patientmanager.exceptions.MyException;
import com.markfox.patientmanager.models.Doctor;
import com.markfox.patientmanager.models.Patient;
import com.markfox.patientmanager.repositories.DoctorRepository;
import com.markfox.patientmanager.services.DoctorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {
    private final DoctorRepository doctorRepository;

    // Constructor for dependency injection of Repository
    public DoctorServiceImpl(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    // Implemented methods
    @Override
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    @Override
    public Doctor getDoctorById(Long id) throws MyException {
        if (id == null || id < 1) {
            throw new MyException("Error: Doctor ID cannot be null");
        }
        return doctorRepository.findById(id).isPresent() ? doctorRepository.findById(id).get() : null;
    }

    @Override
    public List<Patient> getAllDocsPatients(Long id) throws MyException {
        if (id == null || id < 1) {
            throw new MyException("Error: Doctor ID cannot be null");
        }
        return doctorRepository.findById(id).isPresent() ? doctorRepository.findById(id).get().getDocsPatients() : null;
    }

    @Override
    public Doctor addDoctor(Doctor doctor) throws MyException {
        if (doctor == null) {
            throw new MyException("Error: Doctor object cannot be null");
        }
        return doctorRepository.save(doctor);
    }

    @Override
    public void deleteDoctorById(Long id) throws MyException {
        if (id == null || id < 1) {
            throw new MyException("Error: Doctor ID cannot be null");
        }
        doctorRepository.deleteById(id);
    }
}
