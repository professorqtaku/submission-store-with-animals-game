package com.company;

import java.io.Serializable;
import java.util.*;

public class Game implements Serializable {
    private GameMainMenu mainMenu;
    private int playedRounds;
    private int roundToPlay;
    private ArrayList<Player> players;
    private Player currentPlayer;
    private Store store;
    public boolean actionDone;


    public Game(GameMainMenu mainMenu, int playedRounds, int roundToPlay, ArrayList<Player> players){
        this.mainMenu = mainMenu;
        this.playedRounds = playedRounds;
        this.roundToPlay = roundToPlay;
        this.players = players;
        this.store = new Store(this);
    }

    public void startGame(){
        for(var player: players){
            player.game = this;
        }
        currentPlayer = players.get(0);
        do{
            newRound();
            do {
                if(!currentPlayer.losing()){
                    actionDone = false;
                    playerTurn();
                }
                changePlayer();
            } while(currentPlayer != players.get(0));
            playedRounds++;
        } while(playedRounds < roundToPlay || gameOver());
        endGame();
    }

    public void playerTurn(){
        printPlayerStatus();
        printPlayerMenu();
        playerMenuAction(Menu.askPlayerNumber(false,"",9,0));
    }

    public void printPlayerMenu(){
        print("<Choose menu>");
        print("1. Buy dragon");
        print("2. Buy food");
        print("3. Feed your dragons");
        print("4. Breed dragons");
        print("5. Sell dragons");
        print("0. Skip round (any other number is fine)");
    }

    public void playerMenuAction(int action){
        switch(action){
            case 1, 2, 5-> {store.visit(currentPlayer, action);}
            case 3 -> {
                if(currentPlayer.getOwnedDragons().size() == 0 || !currentPlayer.foodAvailable()){
                    print("There is no dragon/food to feed");
                    Menu.sleep(1000);
                    playerTurn();
                }
                else{
                    boolean feedSuccessful = currentPlayer.feedDragonSuccessful();
                    if(!feedSuccessful){
                        playerTurn();
                    }
                }
            }
            case 4 -> currentPlayer.breedDragon();
        }
    }

    public void printPlayerStatus(){
        print("\n".repeat(50));
        print("[" + currentPlayer.getName() + "] Round: " + (playedRounds +1));
        print("Balance: " + currentPlayer.getBalance());
        print("Owned dragons: " + currentPlayer.getOwnedDragons().size());
        if(currentPlayer.getOwnedDragons().size() != 0) {
            print("Name \t (Health) \t Type");
            for (var dragon : currentPlayer.getOwnedDragons()) {
                print(dragon.name + " \t (" + dragon.health + ") \t " + dragon.getClass().getSimpleName());
            }
        }
        print("Owned food: ");
        for(var foodType: currentPlayer.getOwnedFood().keySet()){
            System.out.print(foodType + ": " + currentPlayer.getOwnedFood().get(foodType) + "\t");
        }
        print("\n");
    }

    private void changePlayer(){
        print("[" + currentPlayer.getName() + "] your turn has end. Please turn the computer to next player.");
        Menu.askPlayerNumber(true,"ENTER a number when next player is ready",9,0);
        if(players.indexOf(currentPlayer) == players.size()-1) {
            currentPlayer = players.get(0); //start over
        }
        else
        currentPlayer = players.get(players.indexOf(currentPlayer)+1);
    }

    private void newRound(){
        // lower the dragons health points
        System.out.printf("NEW ROUND! [Round %d]\n", playedRounds+1);
        for(var player: players){
            for(var i = player.getOwnedDragons().size()-1; i >= 0; i--){
                player.getOwnedDragons().get(i).reduceHealth((int)(Math.random()*21)+10);
                if(!player.getOwnedDragons().get(i).living()){
                    System.out.println(TextColour.BLUE + "[" + player.getName() + "]: " + player.getOwnedDragons().get(i).name + " is dead." + TextColour.RESET);
                    player.removeDragon(player.getOwnedDragons().get(i),false);
                }
            }
        }
        boolean save = (Menu.askPlayer(true,
                "Do you want to save? (ENTER \"save\" to save, enter a number to continue)")).equalsIgnoreCase("save");
        Menu.sleep(3000);
        if(save){
            mainMenu.saveGame();
        }
    }

    private boolean gameOver(){
        for(var player: players){
            if(!player.losing()){
                return false;
            }
        }
        return true;
    }

    private void endGame(){
        System.out.println("Thanks for playing! Result:");
        for(var player: players){
            for(var dragon: player.getOwnedDragons()){
                player.removeDragon(dragon, true);
            }
        }
        printWinnerList();
    }
    
    private void printWinnerList(){
        // More info: https://stackoverflow.com/questions/16425127/how-to-use-collections-sort-in-java
        players.sort(new Comparator<Player>() {
            @Override
            public int compare(Player p1, Player p2) {
                return Integer.compare(p2.getBalance(), p1.getBalance());
            }
        });
        for(int i = 0; i < players.size(); i++){
            if(i==0) System.out.println(TextColour.YELLOW);
            System.out.println((i+1) + ". " + players.get(i).getName() + TextColour.RESET);
        }
    }

    private static void print(String x){
        if(!x.equals("")){ System.out.println(x); }
    }

}

