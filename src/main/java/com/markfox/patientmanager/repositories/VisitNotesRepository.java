package com.markfox.patientmanager.repositories;

import com.markfox.patientmanager.models.VisitNotes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Visit Notes Entity Repository
@Repository
public interface VisitNotesRepository extends JpaRepository<VisitNotes, Long> {
}
