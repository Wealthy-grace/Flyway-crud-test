package com.example.demoflywaycrud2025.controller;

import com.example.demoflywaycrud2025.business.impl.ContactService;
import com.example.demoflywaycrud2025.configuration.exceptions.ContactAlreadyExistsException;
import com.example.demoflywaycrud2025.domain.dto.ContactDTO;
import com.example.demoflywaycrud2025.domain.request.CreateContactRequest;
import com.example.demoflywaycrud2025.domain.response.ContactResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/contacts")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class ContactController {

    private final ContactService contactService;

    // Create a single contact
    @PostMapping
    public ResponseEntity<?> createContact(@RequestBody @Valid CreateContactRequest request) {
        try {
            ContactResponse response = contactService.createContact(request);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (ContactAlreadyExistsException e) {
            return new ResponseEntity<>(
                    ContactResponse.builder()
                            .id(null)
                            .message(e.getMessage())
                            .build(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    // Bulk create contacts
    @PostMapping("/bulk")
    public ResponseEntity<?> createContactsBulk(@RequestBody @Valid List<CreateContactRequest> requests) {
        try {
            List<ContactResponse> responses = contactService.createContactsBulk(requests);
            return new ResponseEntity<>(responses, HttpStatus.CREATED);
        } catch (ContactAlreadyExistsException e) {
            // Optionally, return partial success or information about which failed
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContactDTO> getContactById(@PathVariable String id) {
        ContactDTO contact = contactService.getContactById(id);
        if (contact != null) {
            return new ResponseEntity<>(contact, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Get all contacts
    @GetMapping
    public ResponseEntity<List<ContactDTO>> getAllContacts() {
        List<ContactDTO> contacts = contactService.getAllContacts();
        return new ResponseEntity<>(contacts, HttpStatus.OK);
    }

    // Update a contact
    @PutMapping("/{id}")
    public ResponseEntity<?> updateContactById(@PathVariable String id,
                                               @RequestBody @Valid CreateContactRequest request) {
        ContactDTO contact = contactService.UpdateContactById(id, request);
        if (contact != null) {
            return new ResponseEntity<>(contact, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
