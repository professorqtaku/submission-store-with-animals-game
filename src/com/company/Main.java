package com.company;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        // DEADLINE 21/10 wednesday
        //new GameMainMenu();

        ArrayList<Player> players = new ArrayList<>();
        Player jag = new Player.PlayerBuilder("10").balance(10).build();
        Player hej = new Player.PlayerBuilder("4").balance(4).build();
        Player lol = new Player.PlayerBuilder("5").balance(5).build();
        Player manne = new Player.PlayerBuilder("1000").balance(1000).build();

        players.add(jag);
        players.add(hej);
        players.add(lol);
        players.add(manne);

        System.out.println("Sortering");

            players.sort(new Comparator<Player>() {
                @Override
                public int compare(Player p1, Player p2) {
                    return Integer.valueOf(p2.getBalance()).compareTo(p1.getBalance());
                }
            });
        for(int i = 0; i < players.size(); i++){
            if(i==0) System.out.println(TextColour.YELLOW);
            System.out.println((i+1) + players.get(i).getName() + TextColour.RESET);
        }
/*
        Player jag = new Player.PlayerBuilder("Jag").build();
        FireDragon sanna = new FireDragon("Sanna","female", jag);
        sanna.reduceHealth(90);
        FireDragon laura = new FireDragon("menn","female", jag);

        System.out.println(sanna.getClass().getSimpleName() + " " + sanna.name);

        jag.buyFood(new Fruit(), 10);
        jag.buyFood(new Metal(), 10);
        jag.buyFood(new Meat(), 10);
        System.out.println(jag.getOwnedFood().get("Fruit"));


        Dragon mannen = new MetalDragon("mannen", "male", jag);
        jag.feedDragonSuccessful();
        */
    }
}
