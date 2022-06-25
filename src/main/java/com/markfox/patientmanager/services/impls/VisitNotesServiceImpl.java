package com.markfox.patientmanager.services.impls;

import com.markfox.patientmanager.models.VisitNotes;
import com.markfox.patientmanager.repositories.VisitNotesRepository;
import com.markfox.patientmanager.services.VisitNotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VisitNotesServiceImpl implements VisitNotesService {
    private final VisitNotesRepository visitNotesRepository;

    public VisitNotesServiceImpl(VisitNotesRepository repo) {
        this.visitNotesRepository = repo;
    }

    @Override
    public VisitNotes addVisitNotes(VisitNotes notes) {
        return visitNotesRepository.save(notes);
    }

    @Override
    public void deleteVisitNoteById(Long id) {
        visitNotesRepository.deleteById(id);
    }
}
