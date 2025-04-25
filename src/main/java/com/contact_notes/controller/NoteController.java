package com.contact_notes.controller;

import com.contact_notes.dto.NoteDto;
import com.contact_notes.service.NoteService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/contacts/{contactId}/notes")
public class NoteController {
    private final NoteService noteService;
    public NoteController(NoteService noteService) { this.noteService = noteService; }

    @PostMapping
    public NoteDto create(@PathVariable Long contactId, @RequestBody NoteDto dto) {
        return noteService.create(contactId, dto);
    }

    @GetMapping
    public List<NoteDto> list(@PathVariable Long contactId) {
        return noteService.list(contactId);
    }

    @DeleteMapping("/{noteId}")
    public void delete(@PathVariable Long contactId, @PathVariable Long noteId) {
        noteService.delete(contactId, noteId);
    }
}
