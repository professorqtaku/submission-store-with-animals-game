package com.company;

public class Main {

    public static void main(String[] args) {
	// DEADLINE 21/10 wednesday
        //new GameMainMenu();
        Player jag = new Player.PlayerBuilder("Jag").build();
        FireDragon sanna = new FireDragon("menn","female", jag);
        FireDragon laura = new FireDragon("menn","female", jag);
        sanna.breed(laura);
        for(var dragon: jag.getOwnedDragons()){
            System.out.println(dragon.getClass().getSimpleName());
        }
    }
}
