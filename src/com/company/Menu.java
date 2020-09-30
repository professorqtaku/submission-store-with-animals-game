package com.company;

import java.util.Scanner;

public class Menu {
    Game game;
    private static Scanner scanner = new Scanner(System.in);
    public Menu(Game game){
        this.game = game;
    }

    public void printMainMenu(){
        print("*-*-*-* Welcome to \"MONOPOL WITH ANIMALS\"! *-*-*-*");
        print("Choose following:");
        print("1. New game");
        print("2. Load game");
        print("3. End game");
    }

    public static String askPlayer(boolean print, String question){
        if(print){
            print(question);
        }
        print(TextColour.GREEN + "Your choice: " + TextColour.RESET);
        return scanner.next();
    }

    public static int askPlayerANumber(boolean print, String question, int max, int min){
        if(print) {
            print(question);
        }
        print(TextColour.GREEN + "Your choice: " + TextColour.RESET);
        int toReturn;
        do{
            try {
                toReturn = Integer.parseInt(scanner.next());
                if(toReturn > max || toReturn < min){
                    System.out.println("Please enter a number between " + min + "-" + max);
                }
            } catch (Exception e) {
                System.out.println("Please enter an integer!");
                toReturn = -1;
            }
        }while(toReturn == -1 || toReturn > max);
        return toReturn;
    }

    public int askPlayerWithOptions(boolean print, String question, String ...choices){
        if(print){
            System.out.println(question);
            for (int i = 1; i <= choices.length; i++) {
                print(i + " " + choices[i]);
            }
        }
        return askPlayerANumber(false, "", choices.length);
    }

    private static void print(String x){
        // print a string if it is not empty
        if(!x.equals("")){ System.out.println(x); }
    }



}
