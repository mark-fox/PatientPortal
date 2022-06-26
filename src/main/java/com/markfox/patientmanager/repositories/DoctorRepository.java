package com.markfox.patientmanager.repositories;

import com.markfox.patientmanager.models.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Doctor Entity Repository
@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}
