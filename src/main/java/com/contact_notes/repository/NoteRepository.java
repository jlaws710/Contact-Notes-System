package com.contact_notes.repository;

import com.contact_notes.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findByContactId(Long contactId);
}
