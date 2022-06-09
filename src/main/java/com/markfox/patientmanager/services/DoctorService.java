package com.markfox.patientmanager.services;

import com.markfox.patientmanager.models.Doctor;
import com.markfox.patientmanager.models.Patient;

import java.util.List;

public interface DoctorService {
    List<Doctor> getAllDoctors();
    Doctor getDoctorById(Long id);
    List<Patient> getAllDocsPatients(Long id);
}
