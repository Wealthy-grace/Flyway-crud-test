package com.example.demoflywaycrud2025.domain.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactResponse {

    private String id;
    private String name;
    private String message;
    private String email;
    private String phone_number;
    private String employees;
    private String coc_number;
    private String vat_number;
    private String iban_number;
    private String description;
    private Double budget;

}
