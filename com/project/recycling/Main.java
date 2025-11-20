package com.project.recycling;

import java.util.*;
import java.time.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        // Create a Scanner object for reading input
        Scanner scanner = new Scanner(System.in);
        HashMap<Integer, Household> map = new HashMap<>();
        ArrayList<RecyclingEvent> event = new ArrayList<>();

        
        map.put(1, new Household(1, "Mrunal", "Raver", LocalDate.now()));
        event.add(new RecyclingEvent("Plastic", 12.0, LocalDate.now(), 130));
        try {
            FileWriter filewriter = new FileWriter("recycling.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(filewriter);

            while (true) {

                System.out.println(
                        "Press 1 for Household Register\nPress 2 for Log Recycling Events\nPress 3 for Display Reports\nPress 4 for Generate Reports\nPress 5 for Exit");
                System.out.print("Please Select one option : ");
                // Read user input
                Integer userChoice = scanner.nextInt();

                // Consume the newline character left by nextInt()
                scanner.nextLine();

                switch (userChoice) {
                    case 1:
                        // Household Register
                        RecyclingService.registerHousehold(map, bufferedWriter);
                        break;

                    case 2:
                        // Log Recycling Events
                        RecyclingService.logRecyclingEvent(map);
                        // System.out.println("--\n" + map + "\n--\n");
                        break;

                    case 3: // Display Reports
                        System.out.println("-----------------------------------");
                        RecyclingService.displayReports(event, map, bufferedWriter);
                        break;

                    default:
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }
    }
}
