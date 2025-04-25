package com.contact_notes.service;

import com.contact_notes.dto.NoteDto;
import com.contact_notes.model.Contact;
import com.contact_notes.model.Note;
import com.contact_notes.repository.ContactRepository;
import com.contact_notes.repository.NoteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class NoteServiceTest {

    @Mock
    private NoteRepository noteRepository;

    @Mock
    private ContactRepository contactRepository;

    @InjectMocks
    private NoteService noteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateNoteForContact() {
        Contact contact = new Contact();
        contact.setId(1L);

        Note note = new Note();
        note.setBody("Sample note");
        note.setContact(contact);

        NoteDto noteDto = new NoteDto();
        noteDto.setBody("Sample note");

        when(contactRepository.findById(1L)).thenReturn(Optional.of(contact));
        when(noteRepository.save(any(Note.class))).thenReturn(note);

        NoteDto created = noteService.create(1L, noteDto);
        assertEquals("Sample note", created.getBody());
    }

    @Test
    public void testCreateNoteForInvalidContact() {
        when(contactRepository.findById(999L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> noteService.create(999L, new NoteDto()));
    }
}
