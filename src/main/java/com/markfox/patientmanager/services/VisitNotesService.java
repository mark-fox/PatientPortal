package com.markfox.patientmanager.services;

import com.markfox.patientmanager.models.VisitNotes;

import java.util.List;

public interface VisitNotesService {
    VisitNotes addVisitNotes(VisitNotes notes);

    void deleteVisitNoteById(Long id);
}
