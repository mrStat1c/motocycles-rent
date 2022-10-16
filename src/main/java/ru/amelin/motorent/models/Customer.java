package ru.amelin.motorent.models;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class Customer {

    private int id;
    @NotEmpty(message = "Name should not be empty!")
    @Size(min = 2, max = 48, message = "Name should be between 2 and 48 characters")
    private String name;
    @Min(value = 18, message = "Age should be at least 18 year")
    private Integer age;
    @Min(value = 0)
    private Integer driveExperience;
    @NotEmpty(message = "Driver license number should not be empty!")
    @Size(min = 10, max = 10)
    private String driverLicenseNumber;
    @Email
    private String email;
    @Pattern(regexp = "\\+\\d{10}", message = "Phone number should be in this format: \"+\" and 10 digits")
    private String phoneNumber;
}
