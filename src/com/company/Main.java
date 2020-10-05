package com.company;

public class Main {

    public static void main(String[] args) {
	// DEADLINE 21/10 wednesday
        //new GameMainMenu();
/*
        Player jag = new Player.PlayerBuilder("Jag").build();
        FireDragon sanna = new FireDragon("Sanna","female", jag);
        FireDragon laura = new FireDragon("menn","female", jag);

            System.out.println(sanna.getClass().getSimpleName() + " " + sanna.name);

 */
        Store store = new Store();
        System.out.println(store.foodTypes.keySet().size());
        store.printDragonMenu();

    }
}
