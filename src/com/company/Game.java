package com.company;

import java.util.ArrayList;

public class Game {
    private Menu menu;
    private ArrayList<Player> players;
    private int roundToPlay;
    private DragonBreederGame currentGame;
    public Game() {
        menu = new Menu();
        gameMain();
    }

    public void gameMain() {
        menu.printMainMenu();
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
        players = new ArrayList<>();
        roundToPlay = Menu.askPlayerNumber(true, "How many rounds? (5-30)", 30, 5);
        int playerQuantity = Menu.askPlayerNumber(true, "How many players? (1-4)", 4, 1);
        int balanceStart = Menu.askPlayerNumber(true, "What is the start balance? (10-1000)", 1000, 10);
        while(players == null || players.size() < playerQuantity){
            String name = Menu.askPlayer(true,
                    "Name of player " + (players == null ? 1 : players.size()+1));
            players.add(new Player.PlayerBuilder(name)
                    .balance(balanceStart)
                    .build());
        }
        currentGame = new DragonBreederGame(0, roundToPlay, players);
    }

    public void loadGame() {

    }

    public void saveGame() {

    }

    public void endGame() {
    }
}

