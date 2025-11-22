package com.project.recycling;

import java.util.*;
import java.time.*;

public class RecyclingService {

    // Scanner object for taking inputs
    static Scanner scanner = new Scanner(System.in);

    // Register Household
    public static void registerHousehold(HashMap<Integer, Household> map) {
        try {
            // Register Household using InputHelper class
            String name = InputHelper.userName();
            String address = InputHelper.userAddress();
            Integer id = InputHelper.getID();
            // Constructor calling of Household class
            Household household = new Household(id, name, address, InputHelper.userJoinDate());
            // Adding Household to HashMap
            map.put(id, household);
            System.out.println("========================================");
            System.out.println("Household Registered Successfully...!!!");
            System.out.println("========================================\n" + household);
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }
    }

    // Log Recycling Event
    public static void logRecyclingEvent(HashMap<Integer, Household> map) {
        // Recycling Event
        try {
            // Check if Household is empty
            if (map.isEmpty()) {
                System.out.println("No Households Registered");
                return;
            }
            // Taking Household ID as input for adding Recycling Event
            System.out.print("Enter Household ID : ");
            Integer id = scanner.nextInt();
            scanner.nextLine();
            // Check if Household ID exists in HashMap
            if (!map.containsKey(id)) {
                System.out.println("ID Does Not Exist, Please register the household first.");
                return;
            }

            Household h = map.get(id);
            // Taking Recycling Event details as input
            String material = InputHelper.userMaterial();
            double weight = InputHelper.weight();
            Integer ecoPoints = 0;
            ecoPoints += (int) (weight * 10);
            // Constructor calling of RecyclingEvent class
            RecyclingEvent recyclingEvent = new RecyclingEvent(material, weight, InputHelper.eventDate(), ecoPoints);
            h.addRecyclingEvent(recyclingEvent);
            System.out.println("Event Added Successful");
            System.out.println("Updated EcoPoints : " + h.getEcoPoints());
            System.out.println("Total Events Logged for this Household : " + h.getEvent().size());
            System.out
                    .println("******************************\n" + recyclingEvent + "\n******************************");
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }
    }

    // Display Reports
    public static void displayReports(HashMap<Integer, Household> map) {
        try {
            // Check if Household is empty
            if (map.isEmpty()) {
                System.out.println("No Household Registered");
                return;
            }
            // Displaying all Households and their Recycling Events
            for (Map.Entry<Integer, Household> entry : map.entrySet()) {
                System.out.println("Household " + entry.getKey());
                System.out.println("===================================");

                System.out.println(entry.getValue());

                Household h = entry.getValue();
                // Check if Recycling Event is empty
                if (h.getEvent().isEmpty()) {
                    System.out.println("No Recycling Events Logged");
                    System.out.println("-----------------------------------");
                    continue;
                }
                // Displaying all Recycling Events of a Household
                Double totalWeight = 0.0;
                System.out.println("Recycling Events As on :" + LocalDate.now() + "\n");
                // Iterating through all Recycling Events of a Household
                for (RecyclingEvent houseHoldEvent : h.getEvent()) {
                    System.out.println(houseHoldEvent.toString());
                    totalWeight += houseHoldEvent.getWeight();
                    System.out.println();
                }
                // Displaying total weight and EcoPoints of a Household
                System.out.println("Total Weight Recycled: " + totalWeight + " kg");
                System.out.println("Total Eco Points: " + h.getEcoPoints());
                System.out.println("-----------------------------------");
            }
            System.out.println("Total Households Registered : " + map.size());
            System.out.println("===================================");
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }
    }

    public static void generateReports(HashMap<Integer, Household> map) {
        // Generate Reports
        int maxEcoPoints = 0;
        Double totalWeight = 0.0;
        Household h = null;
        // Iterating through all Households and finding the one with maximum EcoPoints
        for (Map.Entry<Integer, Household> entry : map.entrySet()) {
            // Check if EcoPoints of a Household is greater than maxEcoPoints
            if (entry.getValue().getEcoPoints() > maxEcoPoints) {
                maxEcoPoints = entry.getValue().getEcoPoints();
                h = entry.getValue();
            }
            // Calculating total weight of all Recycling Events of a Household
            ArrayList<RecyclingEvent> houseEvents = entry.getValue().getEvent();
            for (int i = 0; i < houseEvents.size(); i++) {
                // Adding weight of a Recycling Event to totalWeight
                totalWeight += houseEvents.get(i).getWeight();
            }
        }
        // Displaying Household with maximum EcoPoints and total weight of all Recycling
        // Events
        System.out.println("Household with Maximum EcoPoints : \n");
        System.out.println("Id : " + h.getUniqueID() + "\nName : " + h.getName() + "\nEcoPoints : " + h.getEcoPoints());
        System.out.println("-----------------------------------");
        System.out.println("Total Weight Recycled : " + totalWeight + " kg");
    }

}