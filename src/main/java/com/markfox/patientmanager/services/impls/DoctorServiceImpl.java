package com.markfox.patientmanager.services.impls;

import com.markfox.patientmanager.models.Doctor;
import com.markfox.patientmanager.models.Patient;
import com.markfox.patientmanager.repositories.DoctorRepository;
import com.markfox.patientmanager.services.DoctorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {
    private DoctorRepository doctorRepository;

    public DoctorServiceImpl(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    @Override
    public Doctor getDoctorById(Long id) {
        return doctorRepository.findById(id).get();
    }

//    @Override
//    public List<Patient> getAllDocsPatients() {
//        return null;
//    }
}
