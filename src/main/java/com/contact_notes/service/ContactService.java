package com.contact_notes.service;

import com.contact_notes.dto.ContactDto;
import com.contact_notes.model.Contact;
import com.contact_notes.repository.ContactRepository;
import com.contact_notes.util.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContactService {
    private final ContactRepository repo;
    public ContactService(ContactRepository repo) { this.repo = repo; }

    public ContactDto create(ContactDto dto) {
        Contact c = new Contact();
        c.setFirstName(dto.getFirstName());
        c.setLastName(dto.getLastName());
        c.setEmail(dto.getEmail());
        c = repo.save(c);
        dto.setId(c.getId());

        return dto;
    }

    public ContactDto get(Long id) {
        Contact c = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Contact", id));
        ContactDto dto = new ContactDto();
        dto.setId(c.getId()); dto.setFirstName(c.getFirstName());
        dto.setLastName(c.getLastName()); dto.setEmail(c.getEmail());

        return dto;
    }

    public List<ContactDto> list() {
        return repo.findAll().stream().map(c -> {
            ContactDto d = new ContactDto();
            d.setId(c.getId()); d.setFirstName(c.getFirstName());
            d.setLastName(c.getLastName()); d.setEmail(c.getEmail());

            return d;
        }).collect(Collectors.toList());
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
