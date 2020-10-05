package com.company;

import java.util.*;

public class Game {
    Scanner scanner = new Scanner(System.in);
    private int playedRounds;
    private int roundToPlay;
    private ArrayList<Player> players;
    private Player currentPlayer;
    private Store store;


    public Game(int playedRounds, int roundToPlay, ArrayList<Player> players){
        this.playedRounds = playedRounds;
        this.roundToPlay = roundToPlay;
        this.players = players;
        this.store = new Store(this);
    }

    public void startGame(){
        currentPlayer = players.get(0);
        do{
            newRound();
            do {
                if(!currentPlayer.losing()){
                playerTurn();
                }
                changePlayer();
            } while(currentPlayer != players.get(0));
            playedRounds++;
        } while(playedRounds<roundToPlay || gameOver());
    }

    public void playerTurn(){
        printPlayerStatus();
        printPlayerMenu();
        playerMenuAction(Menu.askPlayerNumber(false,"",5,1));
    }

    public void printPlayerMenu(){
        print("<Choose menu>");
        print("1. Buy dragon");
        print("2. Buy food");
        print("3. Feed your dragons");
        print("4. Breed dragons");
        print("5. Sell dragons");
    }

    public void playerMenuAction(int action){
        switch(action){
            case 1, 2 -> {store.visit(currentPlayer, action);}
            case 3 -> {
                if(currentPlayer.getOwnedDragons().size() == 0 || !currentPlayer.foodAvailable()){
                    print("There is no dragon/food to feed");
                    playerTurn();
                }
                else{
                    boolean feedSuccessful = currentPlayer.feedDragonSuccessful();
                    if(!feedSuccessful){
                        playerTurn();
                    }
                }
            }
            case 4 -> {}
            case 5 -> {}
        }
    }

    public void printPlayerStatus(){
        print("\n".repeat(50));
        print("[" + currentPlayer.getName() + "]");
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
        Menu.askPlayerNumber(true,"ENTER a number when newt player is ready",10,0);
        if(players.indexOf(currentPlayer) == players.size()-1) {
            currentPlayer = players.get(0); //start over
        }
        else
        currentPlayer = players.get(players.indexOf(currentPlayer)+1);
    }

    private void newRound(){
        // lower the dragons health points
        for(var player: players){

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

    private static void print(String x){
        if(!x.equals("")){ System.out.println(x); }
    }

}

