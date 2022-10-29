package ru.amelin.motorent.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * Entity класс для маппинга на таблицу motocycle
 */
@Data
@Entity
@Table(name = "motocycle")
public class Motocycle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    @NotEmpty
    @Size(max = 45, message = "Model should be no more 45 characters")
    private String model;
    @Column
    @NotEmpty
    @Size(max = 45, message = "Model should be no more 45 characters")
    private String vin;
    //release - зарезервированное слово в MySQL, поэтому нужно взять его в кавычки
    @Column(name = "`release`")
    @Min(value = 99)
    @Max(value = 9999)
    private int releaseYear;
    @Column
    @Min(value = 50)
    @Max(value = 999)
    private int weight;
    @Column
    @Min(value = 1)
    @Max(value = 999)
    private int power;
    @Column
    private String type;
    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

}
