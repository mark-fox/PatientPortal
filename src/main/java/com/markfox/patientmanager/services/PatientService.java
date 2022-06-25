package com.markfox.patientmanager.services;

import com.markfox.patientmanager.models.Patient;
import com.markfox.patientmanager.models.VisitNotes;

import java.util.List;

public interface PatientService {
    List<Patient> getAllPatients();
    Patient getPatientById(Long id);
    Patient updatePatient(Patient patient);
    void deletePatientById(Long id);
    Patient addPatient(Patient patient);
    List<VisitNotes> getAllVisitNotes(Long id);
    void removePatientsDocByDocId(Long id);
}
