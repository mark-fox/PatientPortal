package com.markfox.patientmanager.services;

import com.markfox.patientmanager.models.Patient;
import com.markfox.patientmanager.models.VisitNotes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PatientService {
    List<Patient> getAllPatients();
    Patient getPatientById(Long id);
    Patient getPatientByName(String firstname, String lastname);
    Patient updatePatient(Patient patient);
    void deletePatientById(Long id);
    Patient addPatient(Patient patient);
    List<VisitNotes> getAllVisitNotes(Long id);
}
