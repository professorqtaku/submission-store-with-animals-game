package com.company;

import javax.print.attribute.standard.PrinterMoreInfoManufacturer;
import java.util.ArrayList;

public class Game {
    private int roundPlayed;
    private ArrayList<Player> players;
    private Menu menu;


    public Game(){
        menu = new Menu(this);
        gameStart();
    }

    public void gameStart(){
        menu.printMainMenu();
        int userChoice = menu.askPlayerWithOptions(false, "", "1","2","3");
        menu.mainMenuAction(userChoice);
    }

    public void newGame(){
        players = new ArrayList<>();
        int playerQuantity = Menu.askPlayerANumber(true, "How many players?", 4);
        int balanceStart = Menu.askPlayerANumber(true, "What is the start balance? (Max 1000)", 1000);
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
