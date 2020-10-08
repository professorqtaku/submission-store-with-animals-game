package com.company;

import java.io.Serializable;
import java.nio.file.*;
import java.io.*;
import java.util.ArrayList;

public class Menu implements Serializable {
    private Game currentGame;
    public Menu() {
        mainMenu();
    }

    public void mainMenu() {
        System.out.println("*-*-*-* Welcome to <<DRAGON BREEDER>> *-*-*-*");
        System.out.println("< Main menu >");
        System.out.println("1. New game");
        System.out.println("2. Load game");
        System.out.println("3. How to play");
        System.out.println("4. End game");
        mainMenuAction(Printer.askPlayerNumber(false, "", 4, 1));
    }

    private void mainMenuAction(int action) {
        switch (action) {
            case 1 -> newGame();
            case 2 -> loadGame();
            case 3 -> howToPlay();
            case 4 -> endGame();
        }
    }

    public void newGame() {
        ArrayList<Player> players = new ArrayList<>();
        int roundToPlay = Printer.askPlayerNumber(true, "How many rounds (5-30)?", 30, 5);
        int playerQuantity = Printer.askPlayerNumber(true, "How many players (1-4)?", 4, 1);
        int balanceStart = Printer.askPlayerNumber(true, "What is the start balance(10-10000)?", 10000, 10);
        while(players.size() < playerQuantity){
            String name = Printer.askPlayer(true,
                    "Name of player " + (players.size()+1));
            players.add(new Player.PlayerBuilder(name)
                    .balance(balanceStart)
                    .build());
        }
        currentGame = new Game(this,0, roundToPlay, players);
        currentGame.startGame();
    }

    public void loadGame() {
        String fileToSave = getSaveFileName() + ".ser";
        if(fileToSave.equals(".ser") || TextFileHandler.fileExist()){
            System.out.println("There are no saves!");
            System.out.println("Back to main menu...");
            Printer.sleep(1000);
            mainMenu();
        }
        else if(Files.exists(Paths.get(fileToSave))) {
            try {
                var save = Serializer.deserialize(fileToSave);
                if(save != null) {
                    System.out.println("Load successful");
                    currentGame = (Game) save;
                    currentGame.startGame();
                }

                else{
                    System.out.println(TextColour.RED + "Loading fail" + TextColour.RESET);
                }
            }
            catch(Exception e){
                System.out.println("Error: " + e);
            }
        }
    }

    public void saveGame() {
        int userChoice = Printer.askPlayerWithOptions(true, "Save new file or rewrite existing saves?",
                "New save", "Show saves");
        if(userChoice == 2){
                String fileToDelete = getSaveFileName();
                if(fileToDelete.equals("")){
                    Printer.sleep(500);
                    saveGame();
                }
                try{
                    Files.deleteIfExists(Paths.get(fileToDelete));
                    TextFileHandler.saveWithChange(fileToDelete);
                }
                catch (Exception e){
                    System.out.println("Delete fail. Error: " + e);
                }
        }
        newSaveGame();
        switch(Printer.askPlayerWithOptions(true, "What do you want to do?",
                "Continue game","Load game", "End game")){
            case 1 ->{currentGame.startGame();}
            case 2 -> loadGame();
            case 3 ->{endGame();}
        }
    }

    private void newSaveGame(){
        String fileToSave = Printer.askPlayer(true,"Please ENTER the name of your save");
        TextFileHandler.saveWithChange(fileToSave);
        Serializer.serialize(fileToSave + ".ser", currentGame);
    }

    public void howToPlay(){
        System.out.println(TextColour.YELLOW + "*** HOW TO PLAY ***\n" +
                "You are dragon breeders. \n" +
                "Buy dragons, food or sell dragon at the shop\n" +
                "Dragon's selling price = buying price * health%\n" +
                "Breed dragons of same element to get more dragons (can be done once/round)\n" +
                "When the game is over, all dragons will be sold in the shop.\n" +
                "The breeder with most money wins.\n" + TextColour.RESET);
        Printer.sleep(1000);
        mainMenu();
    }

    private String getSaveFileName(){
        if(TextFileHandler.fileExist()){
            ArrayList<String> saveFileNames = TextFileHandler.readAsArrayList();
            if (saveFileNames.size() != 0) {
                for (int i = 0; i < saveFileNames.size(); i++) {
                    System.out.println((i + 1) + ". " + saveFileNames.get(i));
                }
                return saveFileNames.get((Printer.askPlayerNumber(true, "Choose a file",
                        saveFileNames.size(), 1) - 1));
            }
        }
        System.out.println(TextColour.RED + "There are no save file!" + TextColour.RESET);
        Printer.sleep(500);
        return "";
    }

    public void endGame() {
        System.out.println("Thank you for playing!");
        System.out.println("The game will end now...");
        System.exit(0);
    }
}

