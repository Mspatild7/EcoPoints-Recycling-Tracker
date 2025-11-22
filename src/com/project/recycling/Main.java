package com.project.recycling;

import java.util.*;
import java.time.*;
import java.io.*;
import org.json.*;

public class Main {
    public static void main(String[] args) {
        // Create a Scanner object for reading input
        Scanner scanner = new Scanner(System.in);
        HashMap<Integer, Household> map = new HashMap<>();
        ArrayList<RecyclingEvent> event = new ArrayList<>();
        Integer uniqueID = 0;

        // map.put(1, new Household(1, "Mrunal", "Raver", LocalDate.now()));
        // event.add(new RecyclingEvent("Plastic", 12.0, LocalDate.now(), 130));

        // Auto-Load data during program run , It will work as a Local database
        try {

            File file = new File("recycling.json");
            if (!file.exists()) {
                System.out.println("No Previous Data Found !!!");
            } else {

                FileReader fileReader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                String line;
                StringBuilder data = new StringBuilder();
                while ((line = bufferedReader.readLine()) != null) {
                    data.append(line);
                }
                String jsonString = data.toString();
                JSONArray jsonArray = new JSONArray(jsonString);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject h = jsonArray.getJSONObject(i);
                    int id = h.getInt("uniqueID");
                    LocalDate localDate = LocalDate.parse(h.getString("joiningDate"));
                    Household houseHoldData = new Household(id, h.getString("name"), h.getString("address"), localDate);
                    map.put(id, houseHoldData);
                    JSONArray array = h.getJSONArray("recyclingEvents");
                    for (int j = 0; j < array.length(); j++) {
                        String materialType = array.getJSONObject(j).getString("materialType");
                        Double weight = array.getJSONObject(j).getDouble("weight");
                        LocalDate localDate2 = LocalDate.parse(array.getJSONObject(j).getString("date"));
                        Integer ecoPoints = array.getJSONObject(j).getInt("ecoPoints");
                        houseHoldData
                                .addRecyclingEvent(new RecyclingEvent(materialType, weight, localDate2, ecoPoints));
                    }

                    if (id > uniqueID) {
                        uniqueID = id;
                    }
                    // [{"materialType":"pa","weight":12.0,"date":"2025-11-21","ecoPoints":120}]
                    // map.put(id, h);
                }
                bufferedReader.close();
                InputHelper.uniqueId = uniqueID;
            }
            // System.out.println(jsonArray);

            

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
                        RecyclingService.saveData(map);
                        break;

                    case 6:
                        return;

                    default:
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }
    }
}
