package com.company;

import java.util.LinkedHashMap;
import java.util.Objects;

public class Store {
    private Player visitor;
    private Game game;
    public LinkedHashMap<String, Food> foodTypes; // key = name of food, value = instance of food sub class
    private LinkedHashMap<String, Dragon> dragonTypes;

    public Store(Game game){
        this.game = game;
        addItemsInStore();
    }

    private void addItemsInStore(){
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
        }
    }

    public void sellDragon(){
        printDragonMenu();
        sellDragonAction(
                Objects.requireNonNull(getDragonByIndex(Menu.askPlayerNumber(
                        true, "Which dragon do you want to buy?", dragonTypes.keySet().size(), 1))));
    }

    public void sellFood(){
        printFoodMenu();
        sellFoodMenuAction(Objects.requireNonNull(getFoodByIndex(Menu.askPlayerNumber(
                true, "Which food do you want to buy?", foodTypes.keySet().size(), 1))));

    }

    public void printDragonMenu(){
        System.out.println("*** DRAGONS ***");
        int listCount = 1;
        if(visitor != null) {
            for (var key : dragonTypes.keySet()) {
                if (visitor.getBalance() >= dragonTypes.get(key).getPrice()) {
                    System.out.println(listCount + ". " + dragonTypes);
                    listCount++;
                }
            }
        }
        else{
            for (var key : dragonTypes.keySet()) {
                System.out.println(listCount + ". " + key);
                listCount++;
            }
        }
        if(listCount == 1){
            System.out.println("You do not have money to buy any dragon!");
            game.playerTurn(); // back to action menu
        }
    }

    private String getDragonByIndex(int index){
        int i = 1;
        for(var key: dragonTypes.keySet()){
            if(i == index) return key;
            i++;
        }
        return null;
    }

    private void sellDragonAction(String dragonType){
        System.out.println("You bought a " + dragonType);
        String name = Menu.askPlayer(true, "Please name the " + dragonType + ": ");
        String gender = (Menu.askPlayerNumber(true, "Choose the gender (1 = male, 2 = female)",
                2,1) == 1 ? "male": "female");
        Dragon dragonToSell = null;
        switch(dragonType){
            case "Wood dragon" ->{dragonToSell = new WoodDragon(name,gender,visitor);}
            case "Fire dragon" ->{dragonToSell = new FireDragon(name,gender,visitor);}
            case "Water dragon" ->{dragonToSell = new WaterDragon(name,gender,visitor);}
            case "Earth dragon" ->{dragonToSell = new EarthDragon(name,gender,visitor);}
            case "Metal dragon" ->{dragonToSell = new MetalDragon(name,gender,visitor);}
        }
        visitor.addDragon(dragonToSell);
        boolean buyMore = (Menu.askPlayerNumber(true, "Do you want to buy more food? (1 = yes, 0 = no", 1, 0) == 1);
        if(buyMore){
            sellDragon();
        }
    }

    public void printFoodMenu(){
        System.out.println("*** Food ***");
        int listCount = 1;
        if(visitor != null){
            for (var key : foodTypes.keySet()) {
                if (visitor.getBalance() >= foodTypes.get(key).getPrice()) {
                    System.out.println(listCount + ". " + key);
                    listCount++;
                }
            }
        }
        else{
            for (var key : foodTypes.keySet()) {
                System.out.println(listCount + ". " + key);
                listCount++;
            }
        }
        if(listCount == 1){
            System.out.println("You do not have money to buy food!");
            game.playerTurn(); // back to action menu
        }
    }

    private String getFoodByIndex(int index){
        int i = 1;
        for(var key: foodTypes.keySet()){
            if(i == index) return key;
            i++;
        }
        return null;
    }

    private void sellFoodMenuAction(String foodType){
        int amount = Menu.askPlayerNumber(true, "How much do you want to buy? ",
                visitor.getBalance()/foodTypes.get(foodType).getPrice(),0);
        visitor.buyFood(foodTypes.get(foodType), amount);
        boolean buyMore = (Menu.askPlayerNumber(true, "Do you want to buy more food? (1 = yes, 0 = no", 1, 0) == 1);
        if(buyMore){
            sellFood();
        }
    }

}
