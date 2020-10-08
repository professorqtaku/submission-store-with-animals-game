package com.company;

import com.company.DragonSubClasses.*;
import com.company.FoodSubClasses.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        // DEADLINE 21/10 wednesday
        //new MainMenu();
        ArrayList<Player> players = new ArrayList<>();
        Player hej = new Player.PlayerBuilder("4").balance(4).build();
        Player lol = new Player.PlayerBuilder("5").balance(5).build();
        Player manne = new Player.PlayerBuilder("1000").balance(1000).build();

        players.add(hej);
        players.add(lol);
        players.add(manne);

        Player jag = new Player.PlayerBuilder("Jag").build();
        FireDragon sanna = new FireDragon("Sanna","female", jag);
        sanna.reduceHealth(90);
        FireDragon laura = new FireDragon("menn","male", jag);
        Dragon mannen = new MetalDragon("mannen", "male", jag);

    }
}
