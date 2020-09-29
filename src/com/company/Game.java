package com.company;

public class Game {
    private Menu menu;
    public Game(){
        menu = new Menu();
    }

    public void gameStart(){
        menu.printWelcome();
    }

}
