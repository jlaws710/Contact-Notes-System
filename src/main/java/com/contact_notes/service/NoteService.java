package com.contact_notes.service;

import com.contact_notes.dto.NoteDto;
import com.contact_notes.model.Contact;
import com.contact_notes.model.Note;
import com.contact_notes.repository.ContactRepository;
import com.contact_notes.repository.NoteRepository;
import com.contact_notes.util.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NoteService {
    private final NoteRepository noteRepo;
    private final ContactRepository contactRepo;

    public NoteService(NoteRepository noteRepo, ContactRepository contactRepo) {
        this.noteRepo = noteRepo;
        this.contactRepo = contactRepo;
    }

    public NoteDto create(Long contactId, NoteDto dto) {
        Contact c = contactRepo.findById(contactId)
                .orElseThrow(() -> new ResourceNotFoundException("Contact", contactId));
        Note n = new Note();
        n.setContact(c);
        n.setBody(dto.getBody());
        n = noteRepo.save(n);
        dto.setId(n.getId());

        return dto;
    }

    public List<NoteDto> list(Long contactId) {
        return noteRepo.findByContactId(contactId).stream().map(n -> {
            NoteDto d = new NoteDto();
            d.setId(n.getId()); d.setBody(n.getBody());

            return d;
        }).collect(Collectors.toList());
    }

    public void delete(Long contactId, Long noteId) {
        Note n = noteRepo.findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", noteId));
        if (!n.getContact().getId().equals(contactId)) {
            throw new ResourceNotFoundException("Note", noteId);
        }
        noteRepo.delete(n);
    }
}
