package com.company;

import java.util.*;

public class Player {
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
    private String name;
    private int balance;
    private ArrayList<Dragon> ownedDragons;
    private HashMap<String, Integer> ownedFood; // key = name of food, value = quantity

    public Player(PlayerBuilder builder){
        this.name = builder.name;
        this.balance = builder.balance;
        this.ownedDragons = new ArrayList<>();
        this.ownedFood = new HashMap<>();
        addFoodToList();
    }

    private void addFoodToList(){
        this.ownedFood.put("Fruit", 0);
        this.ownedFood.put("Meat", 0);
        this.ownedFood.put("Metal", 0);
    }

    public ArrayList<Dragon> getOwnedDragons() {
        return ownedDragons;
    }

    public HashMap<String, Integer> getOwnedFood(){ return ownedFood;}

    public boolean losing(){
        return (balance <= 0 && ownedDragons.size() == 0);
    }

    public void buyFood(Food food, int quantity){
        balance -= food.getPrice()*quantity;
        ownedFood.put(food.getClass().getSimpleName(), ownedFood.get(food.getClass().getSimpleName())+quantity);
    }

    public void consumeFood(Food food, int consumedQuantity){
        ownedFood.put(food.getClass().getSimpleName(), ownedFood.get(food.getClass().getSimpleName())-consumedQuantity);
    }

    public void addDragon(Dragon dragon){
        if(dragon != null) {
            if (ownedDragons.contains(dragon)) {
                return;
            }
            ownedDragons.add(dragon);
            dragon.changeOwner(this);
        }
    }

    public void removeDragon(Dragon dragon){
        if(!ownedDragons.contains(dragon)){
            return;
        }
        ownedDragons.remove(dragon);
        dragon.changeOwner(null);
    }

    public String getName(){
        return this.name;
    }

    public int getBalance(){ return this.balance; }


}
