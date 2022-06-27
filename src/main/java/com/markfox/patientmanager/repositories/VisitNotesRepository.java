package com.markfox.patientmanager.repositories;

import com.markfox.patientmanager.models.VisitNotes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// Visit Notes Entity Repository
@Repository
public interface VisitNotesRepository extends JpaRepository<VisitNotes, Long> {
    // Custom query to retrieve Visit Notes by ID
    @Query("FROM VisitNotes v WHERE v.id = :notesId")
    Optional<VisitNotes> getVisitNotesById(@Param("notesId") Long id);
}
