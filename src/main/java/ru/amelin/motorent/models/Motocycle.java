package ru.amelin.motorent.models;

import lombok.Data;

@Data
public class Motocycle {

    private int id;
    private String model;
    private String vin;
    private int releaseYear;
    private int weight;
    private int power;
    private String type;

}
