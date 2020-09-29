package com.company;

import java.util.ArrayList;

public class Player {
    private String name;
    private int balance;
    private ArrayList<Animal> ownedAnimals;

    public Player(PlayerBuilder builder){
        this.name = builder.name;
        this.balance = builder.balance;
        this.ownedAnimals = new ArrayList<>();
    }

    public static class PlayerBuilder {
        private String name;
        private int balance = 0;

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
