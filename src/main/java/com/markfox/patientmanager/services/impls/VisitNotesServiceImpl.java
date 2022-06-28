package com.markfox.patientmanager.services.impls;

import com.markfox.patientmanager.exceptions.MyException;
import com.markfox.patientmanager.models.VisitNotes;
import com.markfox.patientmanager.repositories.VisitNotesRepository;
import com.markfox.patientmanager.services.VisitNotesService;
import org.springframework.stereotype.Service;

// Service class for Visit Notes entity and VisitNotesRepository
@Service
public class VisitNotesServiceImpl implements VisitNotesService {
    private final VisitNotesRepository visitNotesRepository;

    // Constructor for dependency injection of Repository
    public VisitNotesServiceImpl(VisitNotesRepository repo) {
        this.visitNotesRepository = repo;
    }

    // Saves the provided Visit Notes to database
    @Override
    public VisitNotes addVisitNotes(VisitNotes notes) throws MyException {
        if (notes == null) {
            throw new MyException("Error: Visit Notes object cannot be null");
        }
        return visitNotesRepository.save(notes);
    }

    // Deletes the specified Visit Notes by its ID
    @Override
    public void deleteVisitNoteById(Long id) throws MyException {
        if (id == null) {
            throw new MyException("Error: Visit Notes ID cannot be null");
        }
        visitNotesRepository.deleteById(id);
    }
}
