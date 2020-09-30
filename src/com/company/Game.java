package com.company;

import javax.print.attribute.standard.PrinterMoreInfoManufacturer;
import java.util.ArrayList;

public class Game {
    private int roundToPlay;
    private ArrayList<Player> players;
    private Menu menu;


    public Game(){
        menu = new Menu(this);
        gameStart();
    }

    public void gameStart(){
        menu.printMainMenu();
        mainMenuAction(menu.askPlayerWithOptions(false, "", "1","2","3"));
    }

    public void gameRound(){
        
    }

    public void mainMenuAction(int action){
        switch(action){
            case 1 ->{
                newGame();
            }
            case 2 ->{
                loadGame();
            }
            case 3 ->{
                endGame();
            }
        }
    }

    public void newGame(){
        players = new ArrayList<>();
        roundToPlay = Menu.askPlayerANumber(true, "How many rounds? (5-30)", 30, 5);
        int playerQuantity = Menu.askPlayerANumber(true, "How many players? (1-4)", 4, 1);
        int balanceStart = Menu.askPlayerANumber(true, "What is the start balance? (10-1000)", 1000, 10);
        while(players == null || players.size() < playerQuantity){
            String name = Menu.askPlayer(true,
                    "Name of player " + (players == null ? 1 : players.size()+1));
            players.add(new Player.PlayerBuilder(name)
                    .balance(balanceStart)
                    .build());
        }
    }

    public void loadGame(){

    }

    public void saveGame(){

    }

    public void endGame(){}
}
