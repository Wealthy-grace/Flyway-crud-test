package com.example.demoflywaycrud2025.domain.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactDTO {
    private String id;
    private String companyName;
    private String emailAddress;
    private String phoneNumber;
    private String employees;
    private String cocNumber;
    private String vatNumber;
    private String ibanNumber;
    private String description;
    private Double budget;





}