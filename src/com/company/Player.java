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
    private LinkedHashMap<String, Integer> ownedFood; // key = name of food, value = quantity

    public Player(PlayerBuilder builder){
        this.name = builder.name;
        this.balance = builder.balance;
        this.ownedDragons = new ArrayList<>();
        this.ownedFood = new LinkedHashMap<>();
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

    public void consumeFood(String foodType, int consumedQuantity){
        ownedFood.put(foodType, ownedFood.get(foodType)-consumedQuantity);
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

    public boolean feedDragonSuccessful(){
        if(ownedDragons.size() == 0){
            return false;
        }
        System.out.println("Choose the dragon you want to feed:");
        int listCounter = 1;
        System.out.println("Dragon\t (Health)");
        for(var dragon: ownedDragons){
            System.out.println(listCounter + ". " + dragon.name + " \t(" + dragon.health + ")");
            listCounter++;
        }
        Dragon dragonToFeed = ownedDragons.get(Menu.askPlayerNumber(false,"", ownedDragons.size(),1)-1);
        ArrayList<String> foodOptions = foodOptionsAvailable(dragonToFeed);
        if(foodOptions != null){
            feedDragon(dragonToFeed, foodOptions);
            if(Menu.askPlayerNumber(true, "Do you want to feed again? (1 = yes, 0 = no)", 1, 0) == 1){
                if(foodAvailable()) {
                    feedDragonSuccessful();
                }
                System.out.println(foodOptions.size() + " " + foodAvailable());
                System.out.println("You don't have any food.");
            }
            return true;
        }
        System.out.println("You don't have food for the dragon.");
        if(foodAvailable())
            feedDragonSuccessful();
        return false;
    }

    public boolean foodAvailable(){
        boolean toReturn = false;
        for(var foodType: ownedFood.keySet()){
            if(ownedFood.get(foodType) != 0)
                toReturn = true;
        }
        return toReturn;
    }

    private ArrayList<String> foodOptionsAvailable(Dragon dragon){
        ArrayList<String> foodDragonCanEat = new ArrayList<>(Arrays.asList(dragon.getFoodCanEat()));
        ArrayList<String> foodToFeed = new ArrayList<>();
        for(var food: ownedFood.keySet()){
            if(foodDragonCanEat.contains(food) && ownedFood.get(food) != 0){
                foodToFeed.add(food);
            }
        }
        if(foodToFeed.size() == 0){
            return null;
        }
        return foodToFeed;
    }

    private void feedDragon(Dragon dragon, ArrayList<String> food){
        System.out.println("Choose food to feed: ");
        int listCounter = 1;
        for (var foodType : food) {
            System.out.println(listCounter + ". " + foodType + " " + ownedFood.get(foodType) + " kg");
            listCounter++;
        }
        String foodToFeed = food.get(Menu.askPlayerNumber(false, "", food.size(), 1)-1);
        int amount = Menu.askPlayerNumber(true, "How many kg do you wan to feed?", ownedFood.get(foodToFeed),0);
        dragon.eat(foodToFeed, amount);
    }

    public String getName(){
        return this.name;
    }

    public int getBalance(){ return this.balance; }

}
