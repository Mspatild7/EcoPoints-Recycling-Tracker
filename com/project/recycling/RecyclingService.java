package com.project.recycling;

import java.util.*;
import java.io.*;
import java.time.*;

public class RecyclingService {

    static Scanner scanner = new Scanner(System.in);

    public static void registerHousehold(HashMap<Integer, Household> map, BufferedWriter bufferedwriter) {
        try {
            String name = InputHelper.userName();
            String address = InputHelper.userAddress();
            Integer id = InputHelper.getID();
            Household household = new Household(id, name, address, InputHelper.userJoinDate());
            map.put(id, household);
            System.out.println("========================================");
            System.out.println("Household Registered Successfully...!!!");
            /*
             * bufferedwriter.write(household.convertToJson());
             * bufferedwriter.newLine();
             * bufferedwriter.flush();
             */
            System.out.println("========================================\n" + household);
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }
    }

    // Log Recycling Event
    public static void logRecyclingEvent(HashMap<Integer, Household> map, BufferedWriter bufferedwriter) {
        // Recycling Event
        try {

            if (map.isEmpty()) {
                System.out.println("No Households Registered");
                return;
            }

            // bufferedwriter.newLine();
            System.out.print("Enter Household ID : ");
            Integer id = scanner.nextInt();
            scanner.nextLine();
            if (!map.containsKey(id)) {
                System.out.println("ID Does Not Exist, Please register the household first.");
                return;
            }

            Household h = map.get(id);

            String material = InputHelper.userMaterial();
            double weight = InputHelper.weight();
            Integer ecoPoints = 0;
            ecoPoints += (int) (weight * 10);

            RecyclingEvent recyclingEvent = new RecyclingEvent(material, weight, InputHelper.eventDate(), ecoPoints);
            h.addRecyclingEvent(recyclingEvent);
            String json = h.convertToJson();
            bufferedwriter.write(json);
            bufferedwriter.newLine();
            bufferedwriter.flush();
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
    public static void displayReports(HashMap<Integer, Household> map,
            BufferedWriter bufferedwriter) {
        // Display Reports

        try {
            if (map.isEmpty()) {
                System.out.println("No Household Registered");
                return;
            }

            for (Map.Entry<Integer, Household> entry : map.entrySet()) {
                System.out.println("Household " + entry.getKey());
                System.out.println("===================================");

                System.out.println(entry.getValue());

                Household h = entry.getValue();

                if (h.getEvent().isEmpty()) {
                    System.out.println("No Recycling Events Logged");
                    System.out.println("-----------------------------------");
                    continue;
                }
                // h.getEvent();
                Double totalWeight = 0.0;
                System.out.println("Recycling Events As on :" + LocalDate.now() + "\n");
                for (RecyclingEvent houseHoldEvent : h.getEvent()) {

                    System.out.println(houseHoldEvent.toString());
                    totalWeight += houseHoldEvent.getWeight();
                    System.out.println();
                }

                System.out.println("Total Weight Recycled: " + totalWeight + " kg");
                System.out.println("Total Eco Points: " + h.getEcoPoints());
                System.out.println("-----------------------------------");
            }

        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }
    }

    public static void generateReports(HashMap<Integer, Household> map, BufferedWriter bufferedwriter) {
        // Generate Reports
        int maxEcoPoints = 0;
        Double totalWeight = 0.0;
        Household h = null;
        for (Map.Entry<Integer, Household> entry : map.entrySet()) {
            if (entry.getValue().getEcoPoints() > maxEcoPoints) {
                maxEcoPoints = entry.getValue().getEcoPoints();
                h = entry.getValue();
            }
            ArrayList<RecyclingEvent> houseEvents = entry.getValue().getEvent();
            for (int i = 0; i < houseEvents.size(); i++) {
                totalWeight += houseEvents.get(i).getWeight();
            }
        }
        System.out.println("Household with Maximum EcoPoints : \n");
        System.out.println("Id : " + h.getUniqueID() + "\nName : " + h.getName() + "\nEcoPoints : " + h.getEcoPoints());
        System.out.println("-----------------------------------");
        System.out.println("Total Weight Recycled : " + totalWeight + " kg");
    }
}