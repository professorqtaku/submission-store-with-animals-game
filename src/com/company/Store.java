package com.company;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Objects;

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
        }
    }

    public void sellDragon(){
        ArrayList<String> dragonsCanBuy = dragonsPlayerCanBuyMenu();
        sellDragonAction(dragonsCanBuy);
    }

    public void sellFood(){
        printFoodMenu();
        sellFoodMenuAction(Objects.requireNonNull(getFoodByIndex(Menu.askPlayerNumber(
                true, "Which food do you want to buy?", foodTypes.keySet().size(), 1))));

    }

    public ArrayList<String> dragonsPlayerCanBuyMenu(){
        System.out.println("*** DRAGONS ***");
        ArrayList<String> dragonsAvailable = new ArrayList<>();
        if(visitor != null){
            for (var dragon : dragonTypes.keySet()) {
                if(visitor.getBalance() >= dragonTypes.get(dragon).getPrice()){
                    dragonsAvailable.add(dragon);
                    System.out.println(dragonsAvailable.size() + ". " + dragon);
                }
            }
        }
        if(dragonsAvailable.size() == 0){
            System.out.println(TextColour.RED + "You do not have money to buy more dragons!" + TextColour.RESET);
            game.playerTurn(); // back to action menu
        }
        return dragonsAvailable;
    }

    private void sellDragonAction(ArrayList<String> dragonsCanBuy){
        String dragonToBuy = dragonsCanBuy.get(
                Menu.askPlayerNumber(true,"Which dragon do you want to buy?", dragonsCanBuy.size(),1)-1);
        System.out.println("You bought a " + dragonToBuy);
        String name = Menu.askPlayer(true, "Please name the " + dragonToBuy + ": ");
        String gender = (Menu.askPlayerNumber(true, "Choose the gender (1 = male, 2 = female)",
                2,1) == 1 ? "male": "female");
        Dragon dragonToSell = null;
        switch(dragonToBuy){
            case "Wood dragon" ->{dragonToSell = new WoodDragon(name,gender,visitor);}
            case "Fire dragon" ->{dragonToSell = new FireDragon(name,gender,visitor);}
            case "Water dragon" ->{dragonToSell = new WaterDragon(name,gender,visitor);}
            case "Earth dragon" ->{dragonToSell = new EarthDragon(name,gender,visitor);}
            case "Metal dragon" ->{dragonToSell = new MetalDragon(name,gender,visitor);}
        }
        visitor.addDragon(dragonToSell, true);
        boolean buyMore = (Menu.askPlayerNumber(true,
                "Do you want to buy more dragon? (1 = yes, 0 = no)", 1, 0) == 1);
        if(buyMore){
            sellDragon();
        }
    }

    public void printFoodMenu(){
        System.out.println("*** Food ***");
        boolean noMoney = true;
        int listCount = 1;
        if(visitor != null){
            for (var key : foodTypes.keySet()) {
                System.out.println((visitor.getBalance() <= foodTypes.get(key).getPrice() ? TextColour.BLACK : "") +
                        listCount + ". " + key + TextColour.RESET);
                listCount++;
                if(visitor.getBalance() >= foodTypes.get(key).getPrice())
                noMoney = false;
            }
        }
        if(noMoney){
            System.out.println(TextColour.RED + "You do not have money to buy more food!" + TextColour.RESET);
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
        boolean buyMore = (Menu.askPlayerNumber(true, "Do you want to buy more food? (1 = yes, 0 = no)", 1, 0) == 1);
        if(buyMore){
            sellFood();
        }
    }

}
