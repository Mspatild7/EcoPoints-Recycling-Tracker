package com.project.recycling;

import java.io.*;
import java.time.LocalDate;
import java.util.*;
import org.json.*;

public class RecyclingIO {

    // Method to save data to JSON file
    public static void saveData(HashMap<Integer, Household> map) {

        try {
            // Create a FileWriter object to write to the file
            FileWriter filewriter = new FileWriter("recycling.json");
            // Create a BufferedWriter object to write to the file
            BufferedWriter bufferedWriter = new BufferedWriter(filewriter);
            // Write the data to the file
            bufferedWriter.write("[\n");
            int count = map.size();

            // Iterate over the map and write each Household object to the file
            for (Map.Entry<Integer, Household> entry : map.entrySet()) {
                Household h = entry.getValue();
                // Write the Household object to the file
                bufferedWriter.write(h.convertToJson());
                count--;
                if (count > 0) {
                    // Add a comma between Household objects
                    bufferedWriter.write(",\n");
                }
            }
            bufferedWriter.newLine();
            bufferedWriter.write("]\n");
            // Flush the BufferedWriter object to ensure all data is written to the file
            bufferedWriter.flush();
            // Close the BufferedWriter object to release resources
            System.out.println("Data Saved Successfully...!!!");
            bufferedWriter.close();
        } catch (Exception e) {
            System.out.println(
                    "Error : " + e.getMessage());
        }
    }

    public static void loadData(HashMap<Integer, Household> map, ArrayList<RecyclingEvent> Event) {

        Integer uniqueID = 0;
        try {
            // Create a File object for the JSON file
            File file = new File("recycling.json");

            // Check if the file exists
            if (!file.exists()) {
                System.out.println("No Previous Data Found !!!");
                return;
            } else {
                // Create a FileReader object to read from the file
                FileReader fileReader = new FileReader(file);
                // Create a BufferedReader object to read from the file
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                String line;
                StringBuilder data = new StringBuilder();
                // Read the data from the file line by line
                while ((line = bufferedReader.readLine()) != null) {
                    data.append(line);
                }
                // Convert the data to a String
                String jsonString = data.toString();
                // Create a JSONArray object from the JSON string
                JSONArray jsonArray = new JSONArray(jsonString);
                // Iterate over the JSONArray and create Household objects
                for (int i = 0; i < jsonArray.length(); i++) {
                    // Get the JSONObject at the current index
                    JSONObject h = jsonArray.getJSONObject(i);
                    // Create a Household object from the JSONObject
                    int id = h.getInt("uniqueID");
                    LocalDate localDate = LocalDate.parse(h.getString("joiningDate"));
                    Household houseHoldData = new Household(id, h.getString("name"), h.getString("address"), localDate);
                    // Add the Household object to the map
                    map.put(id, houseHoldData);
                    // Get the recycling events array from the JSONObject
                    JSONArray array = h.getJSONArray("recyclingEvents");
                    // Iterate over the recycling events array and create RecyclingEvent objects
                    for (int j = 0; j < array.length(); j++) {
                        // Get the JSONObject at the current index
                        JSONObject jsonObject = array.getJSONObject(j);
                        // Create a RecyclingEvent object from the JSONObject
                        String materialType = jsonObject.getString("materialType");
                        Double weight = jsonObject.getDouble("weight");
                        LocalDate localDate2 = LocalDate.parse(jsonObject.getString("date"));
                        Integer ecoPoints = jsonObject.getInt("ecoPoints");
                        // Add the RecyclingEvent object to the Household object
                        houseHoldData
                                .addRecyclingEvent(new RecyclingEvent(materialType, weight, localDate2, ecoPoints));
                    }
                    // Update the uniqueID variable with the highest ID found in the data
                    if (id > uniqueID) {
                        uniqueID = id;
                    }
                }
                // Close the BufferedReader object to release resources
                bufferedReader.close();
                InputHelper.uniqueId = uniqueID;
            }
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }
    }
}
