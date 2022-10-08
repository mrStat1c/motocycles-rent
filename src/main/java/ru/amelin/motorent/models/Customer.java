package ru.amelin.motorent.models;

import lombok.Data;

@Data
public class Customer {

    private int id;
    private String name;
    private Integer age;
    private Integer driveExperience;
    private String driverLicenseNumber;
    private String email;
    private String phoneNumber;

}
