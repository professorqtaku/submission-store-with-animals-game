package com.company;

import com.company.DragonSubClasses.*;
import com.company.FoodSubClasses.Fruit;
import com.company.FoodSubClasses.Meat;
import com.company.FoodSubClasses.Metal;

import java.util.*;
import java.io.Serializable;

public class Store implements Serializable {
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
        switch(action){
            case 1 -> {sellDragon();}
            case 2 -> {sellFood();}
            case 5 -> {buyDragonFromPlayer();}
        }
    }

    private void sellDragon(){
        ArrayList<String> dragonsCanBuy = dragonsPlayerCanBuyMenu();
        sellDragonAction(dragonsCanBuy);
    }

    private ArrayList<String> dragonsPlayerCanBuyMenu(){
        System.out.println("< Store: DRAGONS >");
        ArrayList<String> dragonsPlayerCanBuy = new ArrayList<String>();
        if(visitor != null){
            for (var dragon : dragonTypes.keySet()) {
                if(visitor.getBalance() >= dragonTypes.get(dragon).getPriceNow()){
                    dragonsPlayerCanBuy.add(dragon);
                    System.out.println(dragonsPlayerCanBuy.size() + ". " + dragon);
                }
            }
        }
        checkListAndWarn(dragonsPlayerCanBuy.size());
        return dragonsPlayerCanBuy;
    }

    private void sellDragonAction(ArrayList<String> dragonsCanBuy){
        int dragonIndex = (Printer.askPlayerNumber(true,
                "Which dragon do you want to buy?", dragonsCanBuy.size(),0)-1);
        backToGame(dragonIndex == -1, !game.actionDone);
        if(dragonIndex >= 0) {
            String dragonToBuy = dragonsCanBuy.get(dragonIndex);
            System.out.println("You bought a " + dragonToBuy);
            String name = Printer.askPlayer(true, "Please name the " + dragonToBuy + ": ");
            String gender = (Printer.askPlayerNumber(true, "Choose the gender (1 = male, 2 = female)",
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

    private void sellFood(){
        ArrayList<String> foodCanBuy = foodPlayerCanBuyMenu();
        sellFoodMenuAction(foodCanBuy);
    }

    private ArrayList<String> foodPlayerCanBuyMenu(){
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
                Printer.askPlayerNumber(true, "What food do you want to buy?",foodCanBuy.size(),1)-1);
        int amount = Printer.askPlayerNumber(true, "How much do you want to buy (0-" +
                        visitor.getBalance()/foodTypes.get(foodToBuy).getPrice() + ")? ",
                visitor.getBalance()/foodTypes.get(foodToBuy).getPrice(),0);
        backToGame(amount == 0, !game.actionDone);
        visitor.buyFood(foodTypes.get(foodToBuy), amount);
        game.actionDone = true;
        if (askBuyMore("Food", true)) {
            sellFood();
        }
    }

    private void buyDragonFromPlayer(){
        checkListAndWarn(visitor.getOwnedDragons().size());
        System.out.println("Which dragon do you want to sell?");
        System.out.println("Dragon (Price)");
        for(var dragon: visitor.getOwnedDragons()){
            System.out.println((visitor.getOwnedDragons().indexOf(dragon) + 1) + ". " +
                    dragon.name + " (" + dragon.getPriceNow() + ")");
        }
        int dragonIndex = Printer.askPlayerNumber(false, "", visitor.getOwnedDragons().size(),0)-1;
        backToGame(dragonIndex == -1, !game.actionDone);
        visitor.removeDragon(visitor.getOwnedDragons().get(dragonIndex),true);
        game.actionDone = true;
        if(askBuyMore("Dragon", false)){
            buyDragonFromPlayer();
        }
    }

    private void checkListAndWarn(int listToCheckSize){
        if(listToCheckSize == 0){
            System.out.println(TextColour.RED + "You do not have enough money/dragons to buy/sell/breed more!" + TextColour.RESET);
            Printer.sleep(2000);
            backToGame(!game.actionDone,true);
        }
    }

    private boolean askBuyMore(String thing, boolean buy){
        return (Printer.askPlayerNumber(true,
                "Do you want to " + (buy ? "buy" : "sell") + " more " +
                        thing.toLowerCase() + "? (1 = yes, 0 = no)", 1, 0) == 1);
    }

    private void backToGame(boolean condition1, boolean condition2){
        //player can choose from player's main menu
        if(condition1 && condition2){
            System.out.println("Going back to menu...");
            Printer.sleep(2000);
            game.playerTurn();
        }
    }
}
