package com.company;

import java.util.ArrayList;

public class Game {
    private int roundPlayed;
    private ArrayList<Player> players;
    private Menu menu;


    public Game(){
        menu = new Menu();
        gameStart();
    }

    public void gameStart(){
        menu.printWelcome();
    }

}
