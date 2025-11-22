package com.project.recycling;

import java.time.*;
import java.util.ArrayList;

public class Household {

    // Household class attributes
    private Integer uniqueID;
    private String name;
    private String address;
    private LocalDate joiningDate;
    private Integer ecoPoints;
    private ArrayList<RecyclingEvent> events;

    // Household class constructor
    public Household(Integer uniqueID, String name, String address, LocalDate joiningDate) {
        this.uniqueID = uniqueID;
        this.name = name;
        this.address = address;
        this.joiningDate = joiningDate;
        this.ecoPoints = 0;
        this.events = new ArrayList<>();
    }

    // Household class methods
    public void addRecyclingEvent(RecyclingEvent e){
        events.add(e);
        ecoPoints += e.getEcoPoints();
    }

    // Getter and Setter methods
    public ArrayList<RecyclingEvent> getEvent(){
        ArrayList<RecyclingEvent> list = new ArrayList<>(events);
        return list;
    }

    public void setEvent(ArrayList<RecyclingEvent> event){
        this.events = event;
    }

    public Integer getUniqueID() {
        return uniqueID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getJoiningDate(){
        return joiningDate;
    }

    public Integer getEcoPoints(){
        return ecoPoints;
    }

    // Method to convert Household object to JSON string
    public String convertToJson() {
        // Start household JSON
        StringBuilder json = new StringBuilder();
        json.append("{");
        json.append("\"uniqueID\":").append(uniqueID).append(",");
        json.append("\"name\":\"").append(name).append("\",");
        json.append("\"address\":\"").append(address).append("\",");
        json.append("\"joiningDate\":\"").append(joiningDate).append("\",");
        json.append("\"ecoPoints\":").append(ecoPoints).append(",");

        // Add recycling events array
        json.append("\"recyclingEvents\":[");
        for (int i = 0; i < events.size(); i++) {
            json.append(events.get(i).convertToJson());
            if (i < events.size() - 1) {
                json.append(","); // Add comma between events
            }
        }
        json.append("]"); // Close events array

        json.append("}"); // Close household object

        return json.toString();
    }

    // Method to convert Household object to string
    @Override
    public String toString() {
        return "Unique ID : " + uniqueID + "\nName :  " + name + "\nAddress : " + address + "\nDate : " + joiningDate + "\nEcoPoints : " + ecoPoints + "\n-----------------------------------";
    }

}
