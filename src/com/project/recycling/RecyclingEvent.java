package com.project.recycling;

import java.time.*;

public class RecyclingEvent {

    // RecyclingEvent class attributes
    private String materialType;
    private double weight;
    private LocalDate date;
    private Integer ecoPoints;

    // RecyclingEvent class constructor
    public RecyclingEvent(String materialType, double weight, LocalDate date, Integer ecoPoints) {
        this.materialType = materialType;
        this.weight = weight;
        this.date = date;
        this.ecoPoints = ecoPoints;
    }

    // Getter and Setter methods
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
    
    // Method to convert RecyclingEvent object to JSON string
    public String convertToJson(){
        return "{" + "\"materialType\":\"" + materialType + "\",\"weight\":" + weight + ",\"date\":\"" + date + "\",\"ecoPoints\":" + ecoPoints + "}";
    }

    // Method to convert RecyclingEvent object to string
    @Override
    public String toString() {
        return "Material Type: " + materialType + "\nWeight: " + weight + "\nDate: " + date + "\nEco Points: "
                + ecoPoints + "\n-----------------------------------";
    }

}
