package com.markfox.patientmanager.services.impls;

import com.markfox.patientmanager.models.Patient;
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
    public Patient getPatientByName(String firstname, String lastname) {
        return null;
    }

    @Override
    public Patient updatePatient(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public void deletePatientById(Long id) {

    }

    @Override
    public Patient addPatient(Patient patient) {
        return patientRepository.save(patient);
    }



//    @Override
//    public List<Patient> getPatientsByDocId(Long docId) {
//        return patientRepository.getPatientsByDocId(docId);
//    }


//    @Override
//    public Page<Patient> getPatientByDocId(Long docId, Pageable pageable) {
//        return patientRepository.findAll(pageable);
//    }

//    @Override
//    public List<Patient> getPatientsByDocId(Long docId) {
//        return patientRepository.findAllById(Iterable<docId>);
//    }
}
