package com.markfox.patientmanager.services;

import com.markfox.patientmanager.exceptions.MyException;
import com.markfox.patientmanager.models.VisitNotes;

// Visit Notes DAO class
public interface VisitNotesService {
    VisitNotes addVisitNotes(VisitNotes notes) throws MyException;

    void deleteVisitNoteById(Long id) throws MyException;
}
