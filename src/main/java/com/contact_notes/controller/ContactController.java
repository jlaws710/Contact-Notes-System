package com.contact_notes.controller;

import com.contact_notes.dto.ContactDto;
import com.contact_notes.service.ContactService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/contacts")
public class ContactController {
    private final ContactService contactService;
    public ContactController(ContactService contactService) { this.contactService = contactService; }

    @PostMapping
    public ContactDto create(@RequestBody ContactDto dto) { return contactService.create(dto); }

    @GetMapping("/{id}")
    public ContactDto get(@PathVariable Long id) { return contactService.get(id); }

    @GetMapping
    public List<ContactDto> list() { return contactService.list(); }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { contactService.delete(id); }
}
