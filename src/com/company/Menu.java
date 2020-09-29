package com.company;

import java.util.Scanner;

public class Menu {
    Game game;
    Scanner scanner = new Scanner(System.in);
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

    public void mainMenuAction(int action){
        switch(action){
            case 1 ->{
                game.newGame();
            }
            case 2 ->{
                game.loadGame();
            }
            case 3 ->{
                game.endGame();
            }
        }
    }


    public int askPlayer(boolean print, String question, String ...choices){
        if(print){
            System.out.println(question);
            for (int i = 1; i <= choices.length; i++) {
                print(i + " " + choices[i]);
            }
        }
        print(TextColour.GREEN + "Your choice: " + TextColour.RESET);
        int userChoice;
        do{
            try {
                userChoice = Integer.parseInt(scanner.next());
                if(userChoice > choices.length){
                    System.out.println("Please enter a number between 1-" + choices.length);
                }
            } catch (Exception e) {
                System.out.println("Please enter an integer!");
                userChoice = -1;
            }
        }while(userChoice == -1 || userChoice > choices.length);
        return userChoice;
    }

    private void print(String x){
        // print a string if it is not empty
        if(!x.equals("")){ System.out.println(x); }
    }



}
