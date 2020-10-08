package com.company;

import java.io.Serializable;
import java.util.*;

public class Player implements Serializable {
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
    public Game game;
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

    public boolean losing(){
        return (balance <= 0 && ownedDragons.size() <= 0);
    }

    public void buyFood(Food food, int quantity){
        balance -= food.getPrice()*quantity;
        ownedFood.put(food.getClass().getSimpleName(), ownedFood.get(food.getClass().getSimpleName())+quantity);
    }

    public void consumeFood(String foodType, int consumedQuantity){
        ownedFood.put(foodType, ownedFood.get(foodType)-consumedQuantity);
    }

    public void addDragon(Dragon dragon, boolean purchase){
        if(dragon != null) {
            if(purchase) balance -= dragon.getPrice();
            if (ownedDragons.contains(dragon)) return;
            ownedDragons.add(dragon);
            dragon.changeOwner(this, false);
        }
    }

    public void removeDragon(Dragon dragon, boolean sell){
        if(!ownedDragons.contains(dragon))
            return;
        if(sell)
            balance += dragon.health * dragon.getPrice();
        ownedDragons.remove(dragon);
        //dragon.changeOwner(null, false); // gives a NullPointerException
    }

    public boolean feedDragonSuccessful(){
        if(ownedDragons.size() == 0) return false;
        System.out.println("Choose the dragon you want to feed:");
        int listCounter = 1;
        System.out.println("Dragon\t (Health)");
        for(var dragon: ownedDragons){
            System.out.println(listCounter + ". " + dragon.name + " \t(" + dragon.health + ")");
            listCounter++;
        }
        Dragon dragonToFeed = ownedDragons.get(Printer.askPlayerNumber(false,"", ownedDragons.size(),1)-1);
        ArrayList<String> foodOptions = foodOptionsAvailable(dragonToFeed);
        if(foodOptions != null){
            feedDragon(dragonToFeed, foodOptions);
            if(Printer.askPlayerNumber(true, "Do you want to feed again? (1 = yes, 0 = no)", 1, 0) == 1){
                if(foodAvailable()) feedDragonSuccessful();
                else System.out.println("You don't have any food.");
            }
            return true;
        }
        System.out.println("You don't have food for the dragon.");
        if(foodAvailable())
            feedDragonSuccessful();
        return false;
    }

    public boolean foodAvailable(){
        for(var foodType: ownedFood.keySet()){
            if(ownedFood.get(foodType) != 0)
                return true;
        }
        return false;
    }

    private ArrayList<String> foodOptionsAvailable(Dragon dragon){
        ArrayList<String> foodDragonCanEat = new ArrayList<>(Arrays.asList(dragon.getFoodCanEat()));
        ArrayList<String> foodToFeed = new ArrayList<>();
        for(var food: ownedFood.keySet()){
            if(foodDragonCanEat.contains(food) && ownedFood.get(food) != 0)
                foodToFeed.add(food);
        }
        if(foodToFeed.size() == 0)
            return null;
        return foodToFeed;
    }

    private void feedDragon(Dragon dragon, ArrayList<String> food){
        System.out.println("Choose food to feed: ");
        int listCounter = 1;
        for (var foodType : food) {
            System.out.println(listCounter + ". " + foodType + " " + ownedFood.get(foodType) + " kg");
            listCounter++;
        }
        String foodToFeed = food.get(Printer.askPlayerNumber(false, "", food.size(), 1)-1);
        int amount = Printer.askPlayerNumber(true, "How many kg do you wan to feed?", ownedFood.get(foodToFeed),0);
        dragon.eat(foodToFeed, amount);
    }

    public void breedDragon(){
        ArrayList<Dragon> breedDragons = new ArrayList<>();
        for(var dragon: ownedDragons){
            System.out.println(breedDragons.size() + ". " + dragon.name + " " + dragon.gender);
        }
        int dragonToBreedIndex = (Printer.askPlayerNumber(true,
                "Choose the dragon you want to breed.",ownedDragons.size(),0)-1);
        backToGame(dragonToBreedIndex == -1, !game.actionDone);
        if(dragonToBreedIndex >= 0) {
            Dragon dragonToBreed = ownedDragons.get(dragonToBreedIndex);
            Dragon partner = chooseDragonPartner(dragonToBreed);
            if (partner != null) {
                dragonToBreed.breed(partner);
                game.actionDone = true;
            }
        }
    }

    private Dragon chooseDragonPartner(Dragon dragon){
        ArrayList<Dragon> potentialDragons = new ArrayList<>();
        for(var partner: ownedDragons){
            if(dragon.gender != partner.gender && dragon.getClass().equals(partner.getClass())){
                potentialDragons.add(partner);
                System.out.println(potentialDragons.size() + ". " + partner.name);
            }
        }
        checkListAndWarn(potentialDragons.size());
        if(potentialDragons.size() > 0) {
            int partnerDragonIndex = (Printer.askPlayerNumber(true, "Choose partner", potentialDragons.size(), 0) - 1);
            backToGame(!game.actionDone, partnerDragonIndex == -1);
            return potentialDragons.get(partnerDragonIndex);
        }
        return null;
    }

    private void checkListAndWarn(int listToCheckSize){
        if(listToCheckSize == 0){
            System.out.println(TextColour.RED + "You do not have any dragon to breed!" + TextColour.RESET);
            Printer.sleep(2000);
            backToGame(!game.actionDone,true);
        }
    }

    private void backToGame(boolean condition1, boolean condition2){
        //player can choose from player's main menu
        if(condition1 && condition2){
            System.out.println("Going back to menu...");
            Printer.sleep(2000);
            game.playerTurn();
        }
    }

    public String getName(){
        return this.name;
    }

    public ArrayList<Dragon> getOwnedDragons() {
        return ownedDragons;
    }

    public HashMap<String, Integer> getOwnedFood(){ return ownedFood;}

    public int getBalance(){ return this.balance; }

}
