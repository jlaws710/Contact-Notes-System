package com.contact_notes.service;

import com.contact_notes.dto.ContactDto;
import com.contact_notes.model.Contact;
import com.contact_notes.repository.ContactRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class ContactServiceTest {

    @Mock
    private ContactRepository contactRepository;

    @InjectMocks
    private ContactService contactService;

    public ContactServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetContactById() {
        Contact contact = new Contact();
        contact.setId(1L);
        contact.setFirstName("John");
        contact.setLastName("Doe");
        contact.setEmail("john.doe@example.com");

        when(contactRepository.findById(1L)).thenReturn(Optional.of(contact));

        ContactDto found = contactService.get(1L);
        assertNotNull(found);
        assertEquals("John", found.getFirstName());
    }

    @Test
    public void testGetContactById_WhenNotExists() {
        when(contactRepository.findById(999L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> contactService.get(999L));
    }
}
