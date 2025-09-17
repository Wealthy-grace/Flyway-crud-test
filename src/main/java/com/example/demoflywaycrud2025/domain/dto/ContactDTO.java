package com.example.flywaydemo.domain.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactDTO {
    private Long id;
    private String companyName;
    private String emailAddress;
    private String telephoneNumber;
    private String employees;
    private String cocNumber;
    private String vatNumber;
    private String ibanNumber;
    private String description;
    private Double budget;





}