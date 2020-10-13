package com.company;

import java.nio.charset.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Arrays;

public class TextFileHandler {
    public static Path file = Paths.get("save-file-list.txt");

    public static void save(ArrayList<String> object){
        try {
            Files.write(file, object, StandardCharsets.UTF_8);
            //object.removeIf(filePath -> !Files.exists(Paths.get(filePath)));
            /*
            switch(object.size()) {
                case 0 -> Files.delete(file); // deletes the text file if the list is empty
                default -> Files.write(file, object, StandardCharsets.UTF_8);
            }
             */
        }
        catch (Exception e){
            System.out.println("Error " + e);
        }
    }

    public static void saveWithChange(String newTextLine){
        ArrayList<String> object = readAsArrayList();
        if(object.contains(newTextLine)) {
            object.remove(newTextLine);
        }
        else {
            object.add(newTextLine);
        }
        save(object);
    }

    public static String read(){
        try {
            if (!fileExist()) {
                return null;
            } else {
                return Files.readString(file, StandardCharsets.UTF_8);
            }
        }
        catch(Exception e){
            System.out.println(e);
            return null;
        }
    }

    public static ArrayList<String> readAsArrayList(){
        try {
            if (!fileExist()) {
                return new ArrayList<>();
            } else {
                ArrayList<String> toReturn = new ArrayList<String>(
                        Arrays.asList(Files.readString(file, StandardCharsets.UTF_8)
                                .replace("\r", "")
                                .split("\n"))
                );
                toReturn.removeIf(filePath -> !Files.exists(Paths.get(filePath))); //updates the list
                save(toReturn);
                return toReturn;
            }
        }
        catch(Exception e){
            System.out.println("Error: " + e);
            return new ArrayList<>();
        }
    }

    public static boolean fileExist(){
        return Files.exists(file);
    }
}
