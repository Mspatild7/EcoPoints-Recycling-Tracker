package com.project.recycling;

import java.util.*;
import java.io.*;

public class RecyclingService {

    static Scanner scanner = new Scanner(System.in);

    public static void registerHousehold(HashMap<Integer, Household> map, BufferedWriter bufferedwriter) {
        try {
            String name = InputHelper.userName();
            String address = InputHelper.userAddress();
            Integer id = InputHelper.getID();
            Household household = new Household(id, name, address, InputHelper.userJoinDate());
            map.put(id, household);
            System.out.println("Household Registered Successfully");
            bufferedwriter.write(household.toString());
            bufferedwriter.newLine();
            bufferedwriter.flush();
            System.out.println("******************************\n" + household + "\n******************************");
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }
    }

    // Log Recycling Event
    public static void logRecyclingEvent(HashMap<Integer, Household> map) {
        // Recycling Event
        if (map.isEmpty()) {
            System.out.println("No Households Registered");
            return;
        }

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
        // scanner.nextLine();
        ecoPoints += (int) (weight * 10);

        RecyclingEvent recyclingEvent = new RecyclingEvent(material, weight, InputHelper.eventDate(), ecoPoints);
        h.addRecyclingEvent(recyclingEvent);
        System.out.println("Event Added Successful");
        System.out.println("Updated EcoPoints : " + h.getEcoPoints());
        System.out.println("Total Events Logged for this Household : " + h.getEvent().size());
        System.out.println("******************************\n" + recyclingEvent + "\n******************************");
    }

    // Display Reports
    public static void displayReports(ArrayList<RecyclingEvent> event, HashMap<Integer, Household> map,
            BufferedWriter bufferedwriter) {
        // Display Reports
        try {
            if (map.isEmpty()) {
                System.out.println("No Household Registered");
                return;
            }

            FileReader fileReader = new FileReader("recycling.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }

        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }
    }

}