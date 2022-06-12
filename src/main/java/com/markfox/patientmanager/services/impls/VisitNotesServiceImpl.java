package com.markfox.patientmanager.services.impls;

import com.markfox.patientmanager.models.VisitNotes;
import com.markfox.patientmanager.repositories.VisitNotesRepository;
import com.markfox.patientmanager.services.VisitNotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VisitNotesServiceImpl implements VisitNotesService {
    private VisitNotesRepository visitNotesRepository;

    public VisitNotesServiceImpl(VisitNotesRepository repo) {
        this.visitNotesRepository = repo;
    }

    @Override
    public List<VisitNotes> getAllVisitNotes() {
        return visitNotesRepository.findAll();
    }

    @Override
    public VisitNotes getVisitNotesById(Long id) {
        return visitNotesRepository.findById(id).get();
    }

    @Override
    public VisitNotes addVisitNotes(VisitNotes notes) {
        return visitNotesRepository.save(notes);
    }
}
