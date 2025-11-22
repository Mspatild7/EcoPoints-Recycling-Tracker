package com.project.recycling;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Create a Scanner object for reading input
        Scanner scanner = new Scanner(System.in);
        HashMap<Integer, Household> map = new HashMap<>();
        ArrayList<RecyclingEvent> event = new ArrayList<>();

         // Auto-Load data during program run , It will work as a Local database
        RecyclingIO.loadData(map, event);

        try {
            while (true) {
                System.out.println("===================================");
                System.out.println(
                        "Press 1 for Household Register\nPress 2 for Log Recycling Events\nPress 3 for Display Reports\nPress 4 for Generate Reports\nPress 5 for Save Data\nPress 6 for Exit");
                System.out.print("Please Select one option : ");
                // Read user input
                Integer userChoice = scanner.nextInt();

                // Consume the newline character left by nextInt()
                scanner.nextLine();

                switch (userChoice) {
                    case 1:
                        // Household Register
                       
                        RecyclingService.registerHousehold(map);
                        break;

                    case 2:
                        // Log Recycling Events
                        RecyclingService.logRecyclingEvent(map);
                        // System.out.println("--\n" + map + "\n--\n");
                        break;

                    case 3: // Display Reports
                        System.out.println("===================================");
                        RecyclingService.displayReports(map);
                        break;

                    case 4: // Generate Reports
                        System.out.println("===================================");
                        RecyclingService.generateReports(map);
                        break;

                    case 5: // Save Data
                        RecyclingIO.saveData(map);
                        break;

                    case 6:
                        return;

                    default:
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }finally{
            scanner.close();
        }
    }
}
