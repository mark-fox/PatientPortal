package com.markfox.patientmanager.services.impls;

import com.markfox.patientmanager.models.Patient;
import com.markfox.patientmanager.models.VisitNotes;
import com.markfox.patientmanager.repositories.PatientRepository;
import com.markfox.patientmanager.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PatientServiceImpl implements PatientService {

//    @Autowired
    private PatientRepository patientRepository;

    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    @Override
    public Patient getPatientById(Long id) {
        return patientRepository.findById(id).get();
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
        return patientRepository.findById(id).get().getPatientVisits();
    }

    public void removePatientsDocByDocId(Long id) {
        patientRepository.updatePatientDocByDocId(id);
    }
}
