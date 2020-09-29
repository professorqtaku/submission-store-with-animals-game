package com.company;

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
        int userChoice = menu.askPlayer(false, "", "1","2","3");
        menu.mainMenuAction(userChoice);
    }

    public void newGame(){

    }

    public void loadGame(){

    }

    public void saveGame(){

    }

    public void endGame(){}

}
