package com.company;

import java.io.Serializable;
import java.util.*;

public class Game implements Serializable {
    public String fileName = "";
    private Menu mainMenu;
    private int playedRounds;
    private int roundToPlay;
    private ArrayList<Player> players;
    private Player currentPlayer;
    private Store store;
    private Hospital hospital;
    private TradingCenter tradingCenter;
    public boolean actionDone;


    public Game(Menu mainMenu, int playedRounds, int roundToPlay, ArrayList<Player> players){
        this.mainMenu = mainMenu;
        this.playedRounds = playedRounds;
        this.roundToPlay = roundToPlay;
        this.players = players;
        this.store = new Store(this);
        this.hospital = new Hospital(this);
        this.tradingCenter = new TradingCenter(this, this.players);
    }

    public void startGame(){
        for(var player: players){
            player.game = this;
        }
        currentPlayer = players.get(0);
        while(playedRounds < roundToPlay && !gameOver()){
            newRound();
            do {
                if(!currentPlayer.losing()){
                    actionDone = false;
                    playerTurn();
                }
                changePlayer();
            } while(currentPlayer != players.get(0));
            playedRounds++;
        }
        endGame();
    }

    public void playerTurn(){
        printPlayerStatus();
        int maxNumberOfChoice = printPlayerMenu();
        playerMenuAction(Printer.askPlayerNumber(false,"",maxNumberOfChoice,0));
    }

    public int printPlayerMenu(){
        print("<Choose menu>");
        print("1. Buy dragon");
        print("2. Buy food");
        print("3. Feed your dragons");
        print("4. Breed dragons");
        print("5. Sell dragons");
        int toReturn = 5;
        if(currentPlayer.getOwnedDragons().size()>0){
            toReturn += 1;
            print(toReturn + ". Heal sick dragons");
        }
        if(players.size() > 1){
            toReturn += 1;
            print(toReturn + ". Trade");
        }
        print("\n0. Skip round");
        return toReturn;
    }

    public void playerMenuAction(int action){
        switch(action){
            case 1, 2, 5-> {store.visit(currentPlayer, action);}
            case 3 -> {
                if(!currentPlayer.feed()){
                    Printer.sleep(1000);
                    playerTurn();
                }
            }
            case 4 -> currentPlayer.breedDragon();
            case 6 -> {
                if(currentPlayer.getOwnedDragons().size()>0){
                    hospital.visit(currentPlayer);
                }
                else tradingCenter.visit(currentPlayer);
            }
            case 7 -> tradingCenter.visit(currentPlayer);
        }
    }

    public void printPlayerStatus(){
        print("\n".repeat(50));
        print(TextColour.YELLOW + "[" + currentPlayer.getName() + "]" + TextColour.RESET
                + " Round: " + (playedRounds +1));
        print("Balance: " + currentPlayer.getBalance());
        print("Owned dragons: " + currentPlayer.getOwnedDragons().size());
        if(currentPlayer.getOwnedDragons().size() != 0) {
            print("Name (Gender):\t Health (Age/Max age) \t Type");
            print("------------------------------");
            for (var dragon : currentPlayer.getOwnedDragons()) {
                System.out.println(dragon);
            }
            print("------------------------------");
        }
        print("Owned food: ");
        for(var foodType: currentPlayer.getOwnedFood().keySet()){
            System.out.print(foodType + ": " + currentPlayer.getOwnedFood().get(foodType) + " \t ");
        }
        print("\n");
    }

    private void changePlayer(){
        if(!currentPlayer.losing()) {
            print(TextColour.YELLOW + "[" + currentPlayer.getName() + "]" + TextColour.RESET +
                    " your turn has ended. Please turn the computer to next player.");
            Printer.askPlayerNumber(true, "ENTER a number when next player is ready", 9, 0);
            Printer.sleep(1000);
        }
        if(players.indexOf(currentPlayer) == players.size()-1) {
            currentPlayer = players.get(0); //start over
        }
        else
        currentPlayer = players.get(players.indexOf(currentPlayer)+1);
    }

    private void newRound(){
        System.out.println("-".repeat(50));
        System.out.printf(TextColour.BLUE + "NEW ROUND! [Round %d]\n" + TextColour.RESET, playedRounds+1);
        saveGame();
        for(var player: players){
            if(player.losing()){
                continue;
            }
            for(var i = player.getOwnedDragons().size()-1; i >= 0; i--){
                player.getOwnedDragons().get(i).reduceHealth();
                player.getOwnedDragons().get(i).age += 1;
                if(!player.getOwnedDragons().get(i).living() || player.getOwnedDragons().get(i).sick){ // check if dead or sick, then die
                    System.out.println(TextColour.BLUE + "[" + player.getName() + "]: " + player.getOwnedDragons().get(i).name + " is dead. Cause: "
                            + (player.getOwnedDragons().get(i).sick ? "Sickness" : "Aging or 0 health")
                            + TextColour.RESET);
                    player.removeDragon(player.getOwnedDragons().get(i),false, 0);
                }
                else if(player.getOwnedDragons().get(i).gettingSick()){ // if sick
                    System.out.println(TextColour.CYAN + "[" + player.getName() + "]: " + player.getOwnedDragons().get(i).name + " is sick." + TextColour.RESET);
                }
                announceLost(player);
            }
        }
        if(playedRounds == 0){
            return;
        }
        Printer.askPlayerNumber(true, "ENTER a number to start ROUND " + (playedRounds+1), 100000,0);
        Printer.sleep(2000);
    }

    public void announceLost(Player player){
        if(player.losing()){
            System.out.println(TextColour.YELLOW + "[" + player.getName() + "] have lost!" + TextColour.RESET);
        }
    }

    private boolean gameOver(){
        if(playedRounds >= roundToPlay){
            return true;
        }
        for(var player: players){
            if(!player.losing()){
                return false;
            }
        }
        return true;
    }

    private void saveGame(){
        String userAnswer = (Printer.askPlayer(true,
                "Do you want to save?\n" +
                        "(ENTER \"save\" to save, " +
                        (gameOver() ? "ENTER  anything else to go back to Main menu)" :
                                "ENTER \"menu\" to go back to Main menu or enter a number to continue)")   ));
        Printer.sleep(2000);
        if(userAnswer.equalsIgnoreCase("save")){
            mainMenu.saveGame();
        }
        else if(userAnswer.equalsIgnoreCase("menu") && !gameOver()){
            mainMenu.mainMenu();
        }
        else if(gameOver()){
            mainMenu.mainMenu();
        }
    }

    private void endGame(){
        System.out.println("Thanks for playing!");
        System.out.println("-".repeat(50));
        System.out.println("< RESULT >");
        for(var player: players){
            for(int i = player.getOwnedDragons().size()-1; i >= 0; i--){
                player.removeDragon(player.getOwnedDragons().get(i), true, player.getOwnedDragons().get(i).getPriceNow());
            }
        }
        printWinnerList();
        saveGame();
    }
    
    private void printWinnerList(){
        // More info: https://stackoverflow.com/questions/16425127/how-to-use-collections-sort-in-java
        players.sort(new Comparator<Player>() {
            @Override
            public int compare(Player p1, Player p2) {
                return Integer.compare(p2.getBalance(), p1.getBalance());
            }
        });
        System.out.println("Name (Balance)");
        for(int i = 0; i < players.size(); i++){
            if(i==0) System.out.print(TextColour.YELLOW);
            System.out.println((i+1) + ". " + players.get(i).getName() +
                    " (" + players.get(i).getBalance() + ")" + TextColour.RESET);
        }
    }

    private static void print(String x){
        if(!x.equals("")){ System.out.println(x); }
    }

}

