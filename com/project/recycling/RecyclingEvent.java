package com.project.recycling;

import java.time.*;

public class RecyclingEvent {
    private String materialType;
    private double weight;
    private LocalDate date;
    private Integer ecoPoints;

    public RecyclingEvent(String materialType, double weight, LocalDate date, Integer ecoPoints) {
        this.materialType = materialType;
        this.weight = weight;
        this.date = date;
        this.ecoPoints = ecoPoints;
    }

    public String getMaterialType() {
        return materialType;
    }

    public double getWeight() {
        return weight;
    }

    public LocalDate getDate() {
        return date;
    }

    public Integer getEcoPoints() {
        return ecoPoints;
    }

    public String toString() {
        return "Material Type: " + materialType + ", Weight: " + weight + ", Date: " + date + ", Eco Points: "
                + ecoPoints;
    }

}
