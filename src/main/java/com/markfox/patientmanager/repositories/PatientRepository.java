package com.markfox.patientmanager.repositories;

import com.markfox.patientmanager.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    @Modifying
    @Transactional
    @Query("update Patient p set p.doc = null where p.doc.docId = :doctorId")
    void updatePatientDocByDocId(@Param("doctorId") Long id);
}
