

package com.example.demoflywaycrud2025.business.impl;

import com.example.demoflywaycrud2025.configuration.exceptions.ContactAlreadyExistsException;
import com.example.demoflywaycrud2025.configuration.exceptions.ContactNotFoundException;
import com.example.demoflywaycrud2025.domain.dto.ContactDTO;
import com.example.demoflywaycrud2025.domain.request.CreateContactRequest;
import com.example.demoflywaycrud2025.domain.response.ContactResponse;
import com.example.demoflywaycrud2025.persistence.entity.Contact;
import com.example.demoflywaycrud2025.persistence.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;

    @Override
    public ContactResponse createContact(CreateContactRequest request) {
        checkIfContactExists(request);

        Contact contactEntity = mapToEntity(mapToDTO(request));

        Contact savedContact = contactRepository.save(contactEntity);

        return buildContactResponse(savedContact, "Contact created successfully");
    }

    @Override
    public List<ContactResponse> createContactsBulk(List<CreateContactRequest> requests) {
        // Check for duplicates for each request before saving
        requests.forEach(this::checkIfContactExists);

        List<Contact> contacts = requests.stream()
                .map(r -> mapToEntity(mapToDTO(r)))
                .collect(Collectors.toList());

        List<Contact> savedContacts = contactRepository.saveAll(contacts);

        return savedContacts.stream()
                .map(c -> buildContactResponse(c, "Contact created successfully"))
                .collect(Collectors.toList());
    }

    @Override
    public ContactDTO getContactById(String id) {
        Contact contact = contactRepository.findById(id)
                .orElseThrow(() -> new ContactNotFoundException("Contact not found with ID: " + id));

        return mapToDTO(contact);
    }

    @Override
    public List<ContactDTO> getAllContacts() {
        return contactRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ContactDTO UpdateContactById(String id, CreateContactRequest request) {
        Contact contact = contactRepository.findById(id)
                .orElseThrow(() -> new ContactNotFoundException("Contact not found with ID: " + id));

        // Update fields
        contact.setCompanyName(request.getCompanyName());
        contact.setEmail(request.getEmailAddress());
        contact.setPhoneNumber(request.getPhone_number());
        contact.setEmployees(request.getEmployees());
        contact.setCocNumber(request.getCoc_number());
        contact.setVatNumber(request.getVat_number());
        contact.setIbanNumber(request.getIbanNumber());
        contact.setDescription(request.getDescription());
        contact.setBudget(request.getBudget());

        Contact updatedContact = contactRepository.save(contact);

        return mapToDTO(updatedContact);
    }

    // Mapping CreateContactRequest to ContactDTO
    private ContactDTO mapToDTO(CreateContactRequest request) {
        return ContactDTO.builder()
                .companyName(request.getCompanyName())
                .emailAddress(request.getEmailAddress())
                .phoneNumber(request.getPhone_number())
                .employees(request.getEmployees())
                .cocNumber(request.getCoc_number())
                .vatNumber(request.getVat_number())
                .ibanNumber(request.getIbanNumber())
                .description(request.getDescription())
                .budget(request.getBudget())
                .build();
    }

    // Mapping Contact entity to ContactDTO
    private ContactDTO mapToDTO(Contact contact) {
        return ContactDTO.builder()
                .id(contact.getId())
                .companyName(contact.getCompanyName())
                .emailAddress(contact.getEmail())
                .phoneNumber(contact.getPhoneNumber())
                .employees(contact.getEmployees())
                .cocNumber(contact.getCocNumber())
                .vatNumber(contact.getVatNumber())
                .ibanNumber(contact.getIbanNumber())
                .description(contact.getDescription())
                .budget(contact.getBudget())
                .build();
    }

    // Mapping ContactDTO to Contact entity
    private Contact mapToEntity(ContactDTO contactDTO) {
        return Contact.builder()
                .id(contactDTO.getId())
                .companyName(contactDTO.getCompanyName())
                .email(contactDTO.getEmailAddress())
                .phoneNumber(contactDTO.getPhoneNumber())
                .employees(contactDTO.getEmployees())
                .cocNumber(contactDTO.getCocNumber())
                .vatNumber(contactDTO.getVatNumber())
                .ibanNumber(contactDTO.getIbanNumber())
                .description(contactDTO.getDescription())
                .budget(contactDTO.getBudget())
                .build();
    }

    private ContactResponse buildContactResponse(Contact contact, String message) {
        return ContactResponse.builder()
                .id(contact.getId())
                .name(contact.getCompanyName())
                .email(contact.getEmail())
                .phone_number(contact.getPhoneNumber())
                .employees(contact.getEmployees())
                .coc_number(contact.getCocNumber())
                .vat_number(contact.getVatNumber())
                .budget(contact.getBudget())
                .iban_number(contact.getIbanNumber())
                .description(contact.getDescription())
                .message(message)
                .build();
    }

    private void checkIfContactExists(CreateContactRequest request) {
        if (contactRepository.existsByCompanyName(request.getCompanyName())) {
            throw new ContactAlreadyExistsException(
                    "Contact already exists with the company name: " + request.getCompanyName());
        }
        if (contactRepository.existsByPhoneNumber(request.getPhone_number())) {
            throw new ContactAlreadyExistsException(
                    "Contact already exists with the telephone number: " + request.getPhone_number());
        }
        if (contactRepository.existsByEmployees(request.getEmployees())) {
            throw new ContactAlreadyExistsException(
                    "Contact already exists with the employees: " + request.getEmployees());
        }
        if (contactRepository.existsByCocNumber(request.getCoc_number())) {
            throw new ContactAlreadyExistsException(
                    "Contact already exists with the CoC number: " + request.getCoc_number());
        }
        if (contactRepository.existsByVatNumber(request.getVat_number())) {
            throw new ContactAlreadyExistsException(
                    "Contact already exists with the VAT number: " + request.getVat_number());
        }
    }
}