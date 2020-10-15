package com.company;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.nio.file.*;
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
        if(savesExist()) {
            System.out.println("\n9. Delete save");
        }
        System.out.println("\n0. Exit game");
        mainMenuAction(Printer.askPlayerNumber(false, "", 9, 0));
    }

    private void mainMenuAction(int action) {
        switch (action) {
            case 1 -> newGame();
            case 2 -> loadGame();
            case 3 -> howToPlay();
            case 9 -> deleteFile();
            case 0 -> exitGame();
            default -> mainMenu();
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
        String fileToSave = getSaveFileName();
        if(fileToSave.equals("")){
            System.out.println("There are no saves!");
            System.out.println("Back to main menu...");
            Printer.sleep(1000);
            mainMenu();
        }
        else if(Files.exists(Paths.get(fileToSave))) {
            try {
                var save = Serializer.deserialize(fileToSave);
                if(save != null) {
                    System.out.println(TextColour.GREEN + "Load successful" + TextColour.RESET);
                    currentGame = (Game) save;
                    currentGame.startGame();
                }

                else{
                    System.out.println(TextColour.RED + "Loading fail \n" + TextColour.RESET);
                    Printer.sleep(1000);
                    mainMenu();
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
                deleteSave(fileToDelete);
        }
        newSaveGame();
        switch(Printer.askPlayerWithOptions(true, "What do you want to do?",
                "Continue game","Exit game")){
            case 1 -> { currentGame.startGame(); }
            case 2 -> { exitGame(); }
        }
    }

    private void newSaveGame(){
        ArrayList<String> saveFileNames = TextFileHandler.readAsArrayList();
        String fileToSave;
        do {
            fileToSave = Printer.askPlayer(true, "Please ENTER the name of your save") + ".ser";
        }while (saveFileNames.contains(fileToSave));
        Serializer.serialize(fileToSave, currentGame);
        TextFileHandler.saveWithChange(fileToSave);
        currentGame.fileName = fileToSave;
    }

    private void deleteFile(){
        String saveToDelete = getSaveFileName();
        System.out.println(saveToDelete);
        deleteSave(saveToDelete);
        mainMenu();
    }

    private boolean savesExist(){
        return TextFileHandler.fileExist() && TextFileHandler.readAsArrayList().size() > 0;
    }

    public void deleteSave(String fileToDelete){
        try{
            if(Files.deleteIfExists(Paths.get(fileToDelete)))
                TextFileHandler.saveWithChange(fileToDelete);
            else
                System.out.println("There is no such file");
        }
        catch (Exception e){
            System.out.println("Delete fail. Error: " + e);
        }
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
                    System.out.println((i + 1) + ". " + saveFileNames.get(i).replace(".ser", ""));
                }
                return saveFileNames.get((Printer.askPlayerNumber(true, "Choose a file",
                        saveFileNames.size(), 1) - 1));
            }
        }
        System.out.println(TextColour.RED + "There are no save file!" + TextColour.RESET);
        Printer.sleep(500);
        return "";
    }

    public void exitGame() {
        System.out.println("Thank you for playing!");
        System.out.println("Exiting the game now...");
        System.exit(0);
    }
}

