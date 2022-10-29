package ru.amelin.motorent.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;


/**
 * Entity класс для маппинга на таблицу customer
 */
@Data
@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    @NotEmpty(message = "Name should not be empty!")
    @Size(min = 2, max = 48, message = "Name should be between 2 and 48 characters")
    private String name;
    @Column
    @Min(value = 18, message = "Age should be at least 18 year")
    private Integer age;
    @Column(name = "driver_exp")
    @Min(value = 0)
    private Integer driveExperience;
    @Column(name = "driver_lic")
    @NotEmpty(message = "Driver license number should not be empty!")
    @Size(min = 10, max = 10)
    private String driverLicenseNumber;
    @Column
    @Email
    private String email;
    @Column(name = "phone")
    @Pattern(regexp = "\\+\\d{11}", message = "Phone number should be in this format: \"+\" and 10 digits")
    private String phoneNumber;
    @OneToMany(mappedBy = "customer")
    private List<Motocycle> motocycleList;
}
