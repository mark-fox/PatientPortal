package com.markfox.patientmanager.services.impls;

import com.markfox.patientmanager.models.VisitNotes;
import com.markfox.patientmanager.repositories.VisitNotesRepository;
import com.markfox.patientmanager.services.VisitNotesService;
import org.springframework.stereotype.Service;

@Service
public class VisitNotesServiceImpl implements VisitNotesService {
    private final VisitNotesRepository visitNotesRepository;

    // Constructor for dependency injection of Repository
    public VisitNotesServiceImpl(VisitNotesRepository repo) {
        this.visitNotesRepository = repo;
    }

    // Implemented methods
    @Override
    public VisitNotes addVisitNotes(VisitNotes notes) {
        return visitNotesRepository.save(notes);
    }

    @Override
    public void deleteVisitNoteById(Long id) {
        visitNotesRepository.deleteById(id);
    }
}
