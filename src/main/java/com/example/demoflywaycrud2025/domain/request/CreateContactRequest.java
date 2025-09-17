
package com.example.flywaydemo.domain.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateContactRequest {

    @NotBlank(message = "Company name is required")
    @Size(max = 255, message = "Company name cannot exceed 255 characters")
    private String companyName;

    // Validate Email Address
    @NotBlank(message = "Email address is required")
    @Email(message = "Invalid email address")
    @Pattern(regexp = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$", message = "Email address must be a valid email address")
    private String emailAddress;
    @NotBlank
    @NotBlank(message = "Telephone number is required")
    @Pattern(regexp = "^(?:\\+31|0)[1-9]\\d{8}$", message = "Telephone number must be a valid Dutch phone number")
    private String telephoneNumber;

    @NotBlank(message = "Number of employees is required")
    @Pattern(regexp = "^\\d+-\\d+$|^\\d+\\+$", message = "Employees must be in format '10-50' or '100+'")
    private String employees;

    @NotBlank(message = "KvK number is required")
    @Pattern(regexp = "^\\d{8}$", message = "KvK number must be an 8-digit number")
    private String cocNumber;

    @NotBlank(message = "VAT number is required")
   @Pattern(regexp = "^NL\\d{9}B\\d{2}$", message = "VAT number must be in the format NL123456789B01")
    private String vatNumber;

    @NotBlank(message = "IBAN is required")
    @Pattern(regexp = "^NL\\d{2}[A-Z]{4}\\d{10}$", message = "IBAN must be a valid Dutch IBAN")
    private String ibanNumber;

    @NotBlank(message = "Description is required")
    @Size(min = 1, max = 1000, message = "Description must be between 1 and 1000 characters")
    private String description;

    @NotNull(message = "Budget is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Budget must be greater than 0")
    @Digits(integer = 10, fraction = 2, message = "Budget must be a valid monetary amount with up to 10 digits and 2 decimal places")
    private Double budget;


}