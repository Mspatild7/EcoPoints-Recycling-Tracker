package com.project.recycling;

import java.io.*;
import java.time.LocalDate;
import java.util.*;
import org.json.*;

public class RecyclingIO {

    public static void saveData(HashMap<Integer, Household> map) {

        try {
            FileWriter filewriter = new FileWriter("recycling.json");
            BufferedWriter bufferedWriter = new BufferedWriter(filewriter);
            bufferedWriter.write("[\n");
            int count = map.size();

            for (Map.Entry<Integer, Household> entry : map.entrySet()) {
                Household h = entry.getValue();
                bufferedWriter.write(h.convertToJson());
                count--;
                if (count > 0) {
                    bufferedWriter.write(",\n");
                }
            }
            bufferedWriter.newLine();
            bufferedWriter.write("]\n");
            bufferedWriter.flush();

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

            File file = new File("recycling.json");
            if (!file.exists()) {
                System.out.println("No Previous Data Found !!!");
                return;
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
                }
                bufferedReader.close();
                InputHelper.uniqueId = uniqueID;
            }
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }
    }
}
