package com.example.flywaydemo.business.impl;

import com.example.flywaydemo.business.ContactService;
import com.example.flywaydemo.configuration.exceptions.ContactAlreadyExistsException;
import com.example.flywaydemo.domain.dto.ContactDTO;
import com.example.flywaydemo.domain.request.CreateContactRequest;
import com.example.flywaydemo.domain.responses.CreateContactResponse;
import com.example.flywaydemo.persistence.entity.ContactEntity;
import com.example.flywaydemo.persistence.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;
    @Override
    public CreateContactResponse createContact(CreateContactRequest request) {
        // Check for duplicate contact using enhanced validation
        checkIfContactExists(request);

        // Map request to Contact entity
        ContactEntity contactEntity = MaptoRequest(MapToContactDTO(request));

        // Save contact to the repository
        ContactEntity savedContact = contactRepository.save(contactEntity);

        // Return response with the new contact ID and success message
        return CreateContactResponse.builder()
                .id(savedContact.getId())
                .message("Contact created successfully")
                .build();
    }

    // Method to check if the contact already exists
    private boolean contactExists(CreateContactRequest request) {
        return contactRepository.existsByTelephoneNumber(request.getTelephoneNumber());
    }

    // Method to map CreateContactRequest to Contact entity



    @Override
    public ContactDTO getContactById(Long id) {
        return null;
    }

    @Override
    public ContactDTO UpdateContactById(Long id, CreateContactRequest request) {
        return null;
    }


    public ContactDTO MapToContactDTO(CreateContactRequest request) {
        return  ContactDTO.builder()

                .companyName(request.getCompanyName())
                .emailAddress(request.getEmailAddress())
                .telephoneNumber(request.getTelephoneNumber())
                .employees(request.getEmployees())
                .cocNumber(request.getCocNumber())
                .vatNumber(request.getVatNumber())
                .ibanNumber(request.getIbanNumber())
                .description(request.getDescription())
                .budget(request.getBudget())
                .build();
    }

    private ContactEntity MaptoRequest(ContactDTO contactDTO) {
        return  ContactEntity.builder()
                .id(contactDTO.getId())
                .companyName(contactDTO.getCompanyName())
                .email(contactDTO.getEmailAddress())
                .telephoneNumber(contactDTO.getTelephoneNumber())
                .employees(contactDTO.getEmployees())
                .cocNumber(contactDTO.getCocNumber())
                .vatNumber(contactDTO.getVatNumber())
                .ibanNumber(contactDTO.getIbanNumber())
                .description(contactDTO.getDescription())
                .budget(contactDTO.getBudget())
                .build();
    }


    // Methos for check if contact already exists

    private void checkIfContactExists(CreateContactRequest request) {


        if (contactRepository.existsByCompanyName(request.getCompanyName())) {
            throw new ContactAlreadyExistsException(
                    "Contact already exists with the company name: " + request.getCompanyName());
        }

        if(contactRepository.existsByTelephoneNumber(request.getTelephoneNumber())) {
            throw new ContactAlreadyExistsException(
                    "Contact already exists with the telephone number: " + request.getTelephoneNumber());
        }

        if(contactRepository.existsByEmployees(request.getEmployees())) {
            throw new ContactAlreadyExistsException(
                    "Contact already exists with the employees: " + request.getEmployees());

        }
        if (contactRepository.existsByCocNumber(request.getCocNumber())) {
            throw new ContactAlreadyExistsException(
                    "Contact already exists with the CoC number: " + request.getCocNumber());
        }
        if (contactRepository.existsByVatNumber(request.getVatNumber())) {
            throw new ContactAlreadyExistsException(
                    "Contact already exists with the VAT number: " + request.getVatNumber());
        }



    }



}

