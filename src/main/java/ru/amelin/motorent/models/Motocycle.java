package ru.amelin.motorent.models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class Motocycle {

    private int id;
    private Integer customerId;
    @NotEmpty
    @Size(max = 45, message = "Model should be no more 45 characters")
    private String model;
    @NotEmpty
    @Size(max = 45, message = "Model should be no more 45 characters")
    private String vin;
    @NotEmpty
    @Pattern(regexp = "\\d{4}", message = "Phone number should be in this format: 4 digits")
    private int releaseYear;
    @NotEmpty
    @Pattern(regexp = "\\d{2,4}", message = "Weight should be in this format: 2-4 digits")
    private int weight;
    @NotEmpty
    @Pattern(regexp = "\\d{1,3}", message = "Power should be in this format: 1-3 digits")
    private int power;
    private String type;

}
