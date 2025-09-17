package com.example.demoflywaycrud2025.persistence.repository;

import com.example.demoflywaycrud2025.persistence.entity.Contact;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ContactRepository extends MongoRepository<Contact, String> {

    Optional<Contact> findByCompanyName(String name);

    boolean existsByCompanyName(String name);

    boolean existsByPhoneNumber(String phoneNumber);

    boolean existsByCocNumber(String cocNumber);

    boolean existsByVatNumber(String vatNumber);

    boolean existsByEmployees(String employees);

}
