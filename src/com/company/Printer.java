package com.company;

import com.company.DragonSubClasses.WoodDragon;

import java.util.*;

public class Printer {
    private static Scanner scanner = new Scanner(System.in);

    public Printer(){}

    public static String askPlayer(boolean print, String question){
        if(print){
            print("\n" + question);
        }
        System.out.print(TextColour.GREEN + "Your choice: " + TextColour.RESET);
        return scanner.nextLine();
    }

    public static int askPlayerNumber(boolean print, String question, int max, int min){
        if(print) {
            print("\n" + question);
        }
        print(TextColour.GREEN + "Your choice: " + TextColour.RESET);
        int toReturn;
        do{
            try {
                toReturn = Integer.parseInt(scanner.nextLine());
                if(toReturn > max || toReturn < min){
                    System.out.println("Please enter a number between " + min + "-" + max);
                }
            } catch (Exception e) {
                System.out.println("Please enter an integer!");
                toReturn = -1;
            }
        }while(toReturn == -1 || toReturn > max || toReturn < min);
        Printer.sleep(300);
        return toReturn;
    }

    public static int askPlayerWithOptions(boolean print, String question, String ...choices){
        if(print){
            System.out.println("\n" + question);
            for (int i = 0; i < choices.length; i++) {
                print((i+1) + ". " + choices[i]);
            }
        }
        return askPlayerNumber(false, "", choices.length, 1);
    }

    public static void print(String x){
        // print a string if it is not empty
        if(!x.equals("")){ System.out.println(x); }
    }

    public static void sleep(int milliseconds){
        try {
            Thread.sleep(milliseconds);
        }
        catch(Exception e){
            System.out.println("Error: " + e);
        }
    }

    public static void printDragonList(ArrayList<Dragon> dragons, String action, String stringToAdd){
        String toAdd = "-";
        int counter = 1;
        System.out.println("Dragon name (Gender): \t Health \t (Age/Max age)\tType\tSICK/NOT\t " + toAdd + " " + stringToAdd);
        for(var dragon: dragons){
            switch (action){
                case "hospital" -> {
                    toAdd += dragon.getPriceNow()/2;
                }
                case "sell" -> {
                    toAdd += dragon.getPriceNow();
                }
            }
            print(counter + ". " + dragon + (toAdd.equals("-") ? "" : toAdd));
            counter++;
        }
    }

}
