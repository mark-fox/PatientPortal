package com.markfox.patientmanager.services.impls;

import com.markfox.patientmanager.models.Patient;
import com.markfox.patientmanager.models.VisitNotes;
import com.markfox.patientmanager.repositories.PatientRepository;
import com.markfox.patientmanager.services.PatientService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;

    // Constructor for dependency injection of Repository
    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    // Implemented methods
    @Override
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    @Override
    public Patient getPatientById(Long id) {
        return patientRepository.findById(id).isPresent() ? patientRepository.findById(id).get() : null;
    }

    @Override
    public Patient updatePatient(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public void deletePatientById(Long id) {
        patientRepository.deleteById(id);
    }

    @Override
    public Patient addPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public List<VisitNotes> getAllVisitNotes(Long id) {
        return patientRepository.findById(id).isPresent() ? patientRepository.findById(id).get().getPatientVisits() : null;
    }

    public void removePatientsDocByDocId(Long id) {
        patientRepository.updatePatientDocByDocId(id);
    }
}
