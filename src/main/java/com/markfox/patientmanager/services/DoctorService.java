package com.markfox.patientmanager.services;

import com.markfox.patientmanager.exceptions.MyException;
import com.markfox.patientmanager.models.Doctor;
import com.markfox.patientmanager.models.Patient;

import java.util.List;

// Doctor DAO class
public interface DoctorService {
    List<Doctor> getAllDoctors();
    Doctor getDoctorById(Long id) throws MyException;
    List<Patient> getAllDocsPatients(Long id) throws MyException;
    Doctor addDoctor(Doctor doctor) throws MyException;
    void deleteDoctorById(Long id) throws MyException;
}
