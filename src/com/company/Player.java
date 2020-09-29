package com.company;

import java.util.ArrayList;

public class Player {
    private String name;
    private int balance;
    private ArrayList<Animal> ownedAnimals;

    public Player(PlayerBuilder builder){
        this.name = builder.name;
        this.balance = builder.balance;
        this.ownedAnimals = builder.ownedAnimals;
    }

    class PlayerBuilder{
        private String name;
        private int balance = 0;
        private ArrayList<Animal> ownedAnimals = new ArrayList<>();

        public void name(String name){
            this.name = name;
        }

        public void balance(int balance){
            this.balance = balance;
        }

        public void ownedAnimals(ArrayList<Animal> animals){
            this.ownedAnimals = ownedAnimals;
        }

        public Player build(){
            Player player = new Player(this);
            validatePlayer(player);
            return player;
        }

        private void validatePlayer(Player player){
            if(player.name == null || player.balance == 0){
                System.out.println(TextColour.YELLOW + "PLAYER NEED NAMES!" + TextColour.RESET);
                System.exit(0);
            }
        }
    }


}
