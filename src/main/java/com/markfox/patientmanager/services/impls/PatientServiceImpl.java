package com.markfox.patientmanager.services.impls;

import com.markfox.patientmanager.exceptions.MyException;
import com.markfox.patientmanager.models.Patient;
import com.markfox.patientmanager.models.VisitNotes;
import com.markfox.patientmanager.repositories.PatientRepository;
import com.markfox.patientmanager.services.PatientService;
import org.springframework.stereotype.Service;

import java.util.List;

// Service class for Patient entity and PatientRepository
@Service
public class PatientServiceImpl implements PatientService {
    private final PatientRepository patientRepository;

    // Constructor for dependency injection of Repository
    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    // Retrieves all Patients in the database to be displayed in a table
    @Override
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    // Retrieves the specified Patient by their ID if they exist
    @Override
    public Patient getPatientById(Long id) throws MyException {
        if (id == null) {
            throw new MyException("Error: Patient ID cannot be null");
        }
        return patientRepository.findById(id).isPresent() ? patientRepository.findById(id).get() : null;
    }

    // Updates the provided Patient in the database by saving over its data
    // while retaining the same ID
    @Override
    public Patient updatePatient(Patient patient) throws MyException {
        if (patient == null) {
            throw new MyException("Error: Patient object cannot be null");
        }
        return patientRepository.save(patient);
    }

    // Deletes the specified Patient from the database
    @Override
    public void deletePatientById(Long id) throws MyException {
        if (id == null) {
            throw new MyException("Error: Patient ID cannot be null");
        }
        patientRepository.deleteById(id);
    }

    // Adds the provided Patient to the database
    @Override
    public Patient addPatient(Patient patient) throws MyException {
        if (patient == null) {
            throw new MyException("Error: Patient object cannot be null");
        }
        return patientRepository.save(patient);
    }

    // Retrieves all Visit Notes that are mapped to the specified Patient
    @Override
    public List<VisitNotes> getAllVisitNotes(Long id) throws MyException {
        if (id == null) {
            throw new MyException("Error: Patient ID cannot be null");
        }
        return patientRepository.findById(id).isPresent() ? patientRepository.findById(id).get().getPatientVisits() : null;
    }

    // Custom query to set each Patients' Doctor attribute to NULL, which occurs
    // prior to deleting a Doctor
    public void removePatientsDocByDocId(Long id) throws MyException {
        if (id == null) {
            throw new MyException("Error: Doctor ID cannot be null");
        }
        patientRepository.updatePatientDocByDocId(id);
    }
}
