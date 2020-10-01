package com.company;

import java.util.*;

public class Player {
    private String name;
    private int balance;
    private ArrayList<Dragon> ownedDragons;
    private HashMap<String, Integer> ownedFood; // key = name of food, value = quantity
    private boolean isLost;

    public Player(PlayerBuilder builder){
        this.name = builder.name;
        this.balance = builder.balance;
        this.ownedDragons = new ArrayList<>();
        this.ownedFood = new HashMap<>();
        this.isLost = builder.isLost;
    }

    public String getName(){
        return this.name;
    }

    public ArrayList<Dragon> getOwnedDragons() {
        return ownedDragons;
    }

    public HashMap<String, Integer> getOwnedFood(){ return ownedFood;}

    public void lose(){
        this.isLost = true;
    }

    public boolean lost(){
        return this.isLost;
    }

    public void buyFood(Food food){}

    public void buyDragon(Dragon dragon){
        if(ownedDragons.contains(dragon)){
            return;
        }
        ownedDragons.add(dragon);
        dragon.changeOwner(this);
    }

    public void sellDragon(Dragon dragon){
        if(!ownedDragons.contains(dragon)){
            return;
        }
        ownedDragons.remove(dragon);
        dragon.changeOwner(null);
    }

    public static class PlayerBuilder {
        private String name;
        private int balance = 0;
        private boolean isLost = false;

        public PlayerBuilder(String name){
            this.name = name;
        }

        public PlayerBuilder balance(int balance){
            this.balance = balance;
            return this;
        }

        public Player build(){
            Player player = new Player(this);
            validatePlayer(player);
            return player;
        }

        private void validatePlayer(Player player){
            if(player.name == null){
                System.out.println(TextColour.YELLOW + "PLAYER NEED NAMES!" + TextColour.RESET);
                System.exit(0);
            }
        }
    }
}
