package com.company;

import java.util.ArrayList;

public class DragonBreederGame {
    private int playedRounds;
    private int roundToPlay;
    private ArrayList<Player> players;
    private int playerNumber;
    private Menu menu;


    public DragonBreederGame(int playedRounds, int roundToPlay, ArrayList<Player> players){
        this.playedRounds = playedRounds;
        this.roundToPlay = roundToPlay;
        this.players = players;
        this.playerNumber = players.size();
        gameStart();
    }

    public void gameStart(){
        for(var player: players){
            System.out.println(player.getName());
        }
    }

    public void gameRound(){
        do{
            playedRounds++;
        } while(playedRounds<roundToPlay);
    }
}

