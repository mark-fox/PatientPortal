package com.markfox.patientmanager.services;

import com.markfox.patientmanager.models.VisitNotes;

import java.util.List;

public interface VisitNotesService {
    List<VisitNotes> getAllVisitNotes();
    VisitNotes getVisitNotesById(Long id);
    VisitNotes addVisitNotes(VisitNotes notes);
}
