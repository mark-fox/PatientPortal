package com.markfox.patientmanager.services;

import com.markfox.patientmanager.models.VisitNotes;

// Visit Notes DAO class
public interface VisitNotesService {
    VisitNotes addVisitNotes(VisitNotes notes);

    void deleteVisitNoteById(Long id);
}
