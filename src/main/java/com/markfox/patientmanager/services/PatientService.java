package com.markfox.patientmanager.services;

import com.markfox.patientmanager.exceptions.MyException;
import com.markfox.patientmanager.models.Patient;
import com.markfox.patientmanager.models.VisitNotes;

import java.util.List;

// Patient DAO class
public interface PatientService {
    List<Patient> getAllPatients();
    Patient getPatientById(Long id) throws MyException;
    Patient updatePatient(Patient patient) throws MyException;
    void deletePatientById(Long id) throws MyException;
    Patient addPatient(Patient patient) throws MyException;
    List<VisitNotes> getAllVisitNotes(Long id) throws MyException;
    void removePatientsDocByDocId(Long id) throws MyException;
}
