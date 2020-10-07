package com.company;

import java.util.ArrayList;

public class GameMainMenu {
    private Game currentGame;
    public GameMainMenu() {
        mainMenu();
    }

    public void mainMenu() {
        Menu.printMainMenu();
        mainMenuAction(Menu.askPlayerNumber(false, "", 4, 1));
    }

    public void mainMenuAction(int action) {
        switch (action) {
            case 1 -> {
                newGame();
            }
            case 2 -> {
                //loadGame();
            }
            case 3 -> {
                //howToPlay();
            }
            case 4 -> {
                //endGame();
            }
        }
    }

    public void newGame() {
        ArrayList<Player> players = new ArrayList<>();
        int roundToPlay = Menu.askPlayerNumber(true, "How many rounds (5-30)?", 30, 5);
        int playerQuantity = Menu.askPlayerNumber(true, "How many players (1-4)?", 4, 1);
        int balanceStart = Menu.askPlayerNumber(true, "What is the start balance(10-10000)?", 10000, 10);
        while(players.size() < playerQuantity){
            String name = Menu.askPlayer(true,
                    "Name of player " + (players.size()+1));
            players.add(new Player.PlayerBuilder(name)
                    .balance(balanceStart)
                    .build());
        }
        currentGame = new Game(this,0, roundToPlay, players);
        currentGame.startGame();
    }

    public void loadGame() {

    }

    public void saveGame() {
        int userChoice = Menu.askPlayerWithOptions(true, "Save new file or rewrite existing saves?",
                "New save", "Show saves");

        switch(userChoice){
            case 1 ->{ //new save
                String saveName = Menu.askPlayer(true,"Please ENTER the name of your save");
            }
            case 2 ->{
            }
        }

    }

    public void endGame() {
    }
}

