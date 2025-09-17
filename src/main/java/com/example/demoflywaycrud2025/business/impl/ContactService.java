package com.example.flywaydemo.business;

import com.example.flywaydemo.domain.dto.ContactDTO;
import com.example.flywaydemo.domain.request.CreateContactRequest;
import com.example.flywaydemo.domain.responses.CreateContactResponse;

public interface ContactService {

    CreateContactResponse createContact(CreateContactRequest request);

    ContactDTO getContactById(Long id);

    ContactDTO UpdateContactById(Long id, CreateContactRequest request);
}
