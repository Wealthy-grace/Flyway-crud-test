package com.example.demoflywaycrud2025.business.impl;


import com.example.demoflywaycrud2025.domain.dto.ContactDTO;
import com.example.demoflywaycrud2025.domain.request.CreateContactRequest;
import com.example.demoflywaycrud2025.domain.response.ContactResponse;

import java.util.List;

public interface ContactService {

    ContactResponse createContact(CreateContactRequest request);

    ContactDTO getContactById(String id);

    ContactDTO UpdateContactById(String id, CreateContactRequest request);

    // gerAllContacts

    //ContactDTO getAllContacts();

    public List<ContactDTO> getAllContacts();

    public List<ContactResponse> createContactsBulk(List<CreateContactRequest> requests);
}
