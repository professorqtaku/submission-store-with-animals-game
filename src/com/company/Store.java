package com.company;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Store {
    private Player visitor;
    private Game game;
    public LinkedHashMap<String, Food> foodTypes; // key = name of food, value = instance of food sub class
    private LinkedHashMap<String, Dragon> dragonTypes;

    public Store(Game game){
        this.game = game;
        addItemsToStore();
    }

    private void addItemsToStore(){
        foodTypes = new LinkedHashMap<String, Food>();
        foodTypes.put("Fruit", new Fruit());
        foodTypes.put("Meat", new Meat());
        foodTypes.put("Metal", new Metal());
        dragonTypes = new LinkedHashMap<String, Dragon>();
        dragonTypes.put("Wood dragon", new WoodDragon(null,null,null));
        dragonTypes.put("Water dragon", new WaterDragon(null,null,null));
        dragonTypes.put("Fire dragon", new FireDragon(null,null,null));
        dragonTypes.put("Earth dragon", new EarthDragon(null,null,null));
        dragonTypes.put("Metal dragon", new MetalDragon(null,null,null));
    }

    public void visit(Player visitor, int action){
        this.visitor = visitor;
        storeMenu(action);
    }

    private void storeMenu(int action){
        switch(action){
            case 1 -> {sellDragon();}
            case 2 -> {sellFood();}
            case 4 -> {breedDragon();}
            case 5 -> {buyDragonFromPlayer();}
        }
    }

    public void sellDragon(){
        ArrayList<String> dragonsCanBuy = dragonsPlayerCanBuyMenu();
        sellDragonAction(dragonsCanBuy);
    }

    public ArrayList<String> dragonsPlayerCanBuyMenu(){
        System.out.println("< Store: DRAGONS >");
        ArrayList<String> dragonsAvailable = new ArrayList<String>();
        if(visitor != null){
            for (var dragon : dragonTypes.keySet()) {
                if(visitor.getBalance() >= dragonTypes.get(dragon).getPrice()){
                    dragonsAvailable.add(dragon);
                    System.out.println(dragonsAvailable.size() + ". " + dragon);
                }
            }
        }
        checkListAndWarn(dragonsAvailable.size());
        return dragonsAvailable;
    }

    private void sellDragonAction(ArrayList<String> dragonsCanBuy){
        int dragonIndex = (Menu.askPlayerNumber(true,"Which dragon do you want to buy?", dragonsCanBuy.size(),0)-1);
        backToGame(dragonIndex == -1, !game.actionDone);
        if(dragonIndex >= 0) {
            String dragonToBuy = dragonsCanBuy.get(dragonIndex);
            System.out.println("You bought a " + dragonToBuy);
            String name = Menu.askPlayer(true, "Please name the " + dragonToBuy + ": ");
            String gender = (Menu.askPlayerNumber(true, "Choose the gender (1 = male, 2 = female)",
                    2, 1) == 1 ? "male" : "female");
            Dragon dragonToSell = null;
            switch (dragonToBuy) {
                case "Wood dragon" -> {
                    dragonToSell = new WoodDragon(name, gender, visitor);
                }
                case "Fire dragon" -> {
                    dragonToSell = new FireDragon(name, gender, visitor);
                }
                case "Water dragon" -> {
                    dragonToSell = new WaterDragon(name, gender, visitor);
                }
                case "Earth dragon" -> {
                    dragonToSell = new EarthDragon(name, gender, visitor);
                }
                case "Metal dragon" -> {
                    dragonToSell = new MetalDragon(name, gender, visitor);
                }
            }
            visitor.addDragon(dragonToSell, true);
            game.actionDone = true;
            if (askBuyMore("Dragons", true)) {
                sellDragon();
            }
        }
    }

    public void sellFood(){
        ArrayList<String> foodCanBuy = foodPlayerCanBuyMenu();
        sellFoodMenuAction(foodCanBuy);
    }

    public ArrayList<String> foodPlayerCanBuyMenu(){
        System.out.println("< Store: FOOD >");
        ArrayList<String> foodCanBuy = new ArrayList<String>();
        if(visitor != null){
            for (var key : foodTypes.keySet()) {
                if(visitor.getBalance() >= foodTypes.get(key).getPrice()){
                    foodCanBuy.add(key);
                    System.out.println(foodCanBuy.size() + ". " + key);
                }
            }
        }
        checkListAndWarn(foodCanBuy.size());
        return foodCanBuy;
    }

    private void sellFoodMenuAction(ArrayList<String> foodCanBuy){
        String foodToBuy = foodCanBuy.get(
                Menu.askPlayerNumber(true, "What food do you want to buy?",foodCanBuy.size(),1)-1);
        int amount = Menu.askPlayerNumber(true, "How much do you want to buy (0-" +
                        visitor.getBalance()/foodTypes.get(foodToBuy).getPrice() + ")? ",
                visitor.getBalance()/foodTypes.get(foodToBuy).getPrice(),0);
        backToGame(amount == 0, !game.actionDone);
        visitor.buyFood(foodTypes.get(foodToBuy), amount);
        game.actionDone = true;
        if (askBuyMore("Food", true)) {
            sellFood();
        }
    }

    public void buyDragonFromPlayer(){
        checkListAndWarn(visitor.getOwnedDragons().size());
        System.out.println("Which dragon do you want to sell?");
        System.out.println("Dragon (Price)");
        for(var dragon: visitor.getOwnedDragons()){
            System.out.println((visitor.getOwnedDragons().indexOf(dragon) + 1) + ". " +
                    dragon.name + " (" + dragon.getPrice()*dragon.health/100 + ")");
        }
        int dragonIndex = Menu.askPlayerNumber(false, "", visitor.getOwnedDragons().size(),0)-1;
        backToGame(dragonIndex == -1, !game.actionDone);
        visitor.removeDragon(visitor.getOwnedDragons().get(dragonIndex),true);
        game.actionDone = true;
        if(askBuyMore("Dragon", false)){
            buyDragonFromPlayer();
        }
    }

    public void breedDragon(){
        ArrayList<Dragon> breedDragons = new ArrayList<>();
        for(var dragon: visitor.getOwnedDragons()){
            if(dragon.canBreed()) {
                breedDragons.add(dragon);
                System.out.println(breedDragons.size() + ". " + dragon.name + " " + dragon.gender);
            }
        }
        checkListAndWarn(breedDragons.size());
        int dragonToBreedIndex = (Menu.askPlayerNumber(true,
                "Choose the dragon you want to breed.",visitor.getOwnedDragons().size(),0)-1);
        backToGame(dragonToBreedIndex == -1, !game.actionDone);
        if(dragonToBreedIndex >= 0) {
            Dragon dragonToBreed = breedDragons.get(dragonToBreedIndex);
            Dragon partner = chooseDragonPartner(dragonToBreed);
            if (partner != null) {
                dragonToBreed.breed(partner);
                game.actionDone = true;
            }
        }
    }

    private Dragon chooseDragonPartner(Dragon dragon){
        ArrayList<Dragon> potentialDragons = new ArrayList<>();
        for(var partner: visitor.getOwnedDragons()){
            if(dragon.gender != partner.gender && dragon.getClass().equals(partner.getClass()) && partner.canBreed()){
                potentialDragons.add(partner);
                System.out.println(potentialDragons.size() + ". " + partner.name);
            }
        }
        checkListAndWarn(potentialDragons.size());
        if(potentialDragons.size() > 0) {
            int partnerDragonIndex = (Menu.askPlayerNumber(true, "Choose partner", potentialDragons.size(), 0) - 1);
            backToGame(!game.actionDone, partnerDragonIndex == -1);
            return potentialDragons.get(partnerDragonIndex);
        }
        return null;
    }

    private void checkListAndWarn(int listToCheckSize){
        if(listToCheckSize == 0){
            System.out.println(TextColour.RED + "You do not have enough money/dragons to buy/sell/breed more!" + TextColour.RESET);
            Menu.sleep(2000);
            backToGame(!game.actionDone,true);
        }
    }

    private boolean askBuyMore(String thing, boolean buy){
        return (Menu.askPlayerNumber(true,
                "Do you want to " + (buy ? "buy" : "sell") + " more " +
                        thing.toLowerCase() + "? (1 = yes, 0 = no)", 1, 0) == 1);
    }

    private void backToGame(boolean condition1, boolean condition2){
        //player can choose from player's main menu
        if(condition1 && condition2){
            System.out.println("Going back to menu...");
            Menu.sleep(2000);
            game.playerTurn();
        }
    }
}
