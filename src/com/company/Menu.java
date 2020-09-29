package com.company;

import java.util.Scanner;

public class Menu {
    Scanner scanner = new Scanner(System.in);
    public Menu(){
    }

    public void printMainMenu(){
        print("*-*-*-* Welcome to \"MONOPOL WITH ANIMALS\"! *-*-*-*");
        print("Choose following:");
        print("1. New game");
        print("2. Load game");
        print("3. End game");
        askPlayer(false, "", "1","2","3");
    }


    public void askPlayer(boolean print, String question, String ...choices){
        if(print){
            System.out.println(question);
            for (int i = 1; i <= choices.length; i++) {
                print(i + " " + choices[i]);
            }
        }
        print(TextColour.GREEN + "Your choice: " + TextColour.RESET);
        try{
            String userChoice = scanner.next();
        }
        catch(Exception e){
            System.out.println("Error: "e);
        }
    }

    private void print(String x){
        // print a string if it is not empty
        if(!x.equals("")){ System.out.println(x); }
    }



}
