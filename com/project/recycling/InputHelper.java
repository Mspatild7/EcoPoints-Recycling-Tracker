package com.project.recycling;

import java.time.LocalDate;
import java.util.*;

public class InputHelper {

    static Scanner scanner = new Scanner(System.in);
    static Integer uniqueId = 0;
    //Inputs for Househld Register
    public static Integer getID() {
        ++uniqueId;
        return uniqueId;
    }

    public static String userName() {
        System.out.print("Enter your name : ");
        String name = scanner.nextLine();
        return name;
    }

    public static String userAddress(){
        System.out.print("Enter your address : ");
        String address = scanner.nextLine();
        return address;
    }

    public static LocalDate userJoinDate(){
        return LocalDate.now();
    }

    //Input for Recycling Event

    public static String userMaterial(){
        System.out.print("Enter the material : ");
        //scanner.nextLine();
        String material = scanner.nextLine();
        
        if(material.isEmpty()){
            throw new IllegalArgumentException("Material cannot be empty");
        }        
        return material;
    }

    public static double weight(){
        try{
            System.out.print("Enter the weight : ");
            double weight = scanner.nextDouble();
            scanner.nextLine();
            if(weight <= 0){
                throw new IllegalArgumentException("Weight must be greater than 0");
            }
            return weight;  
        }catch(Exception e){
            System.out.println("Invalid input : " + e.getMessage());
        }
        return 0;
    }

    public static LocalDate eventDate(){
        return LocalDate.now();
    }

}
