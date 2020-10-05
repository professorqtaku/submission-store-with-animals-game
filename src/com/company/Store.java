package com.company;

import java.util.ArrayList;
import java.util.HashMap;

public class Store {
    private Player visitor;
    public HashMap<String, Food> foodTypes; // key = name of food, value = instance of food sub class
    private HashMap<String, Dragon> dragonTypes;

    public Store(){
        foodTypes = new HashMap<String, Food>();
        foodTypes.put("Fruit", new Fruit());
        foodTypes.put("Meat", new Meat());
        foodTypes.put("Metal", new Metal());
        dragonTypes = new HashMap<String, Dragon>();
        dragonTypes.put("Wood dragon", new WoodDragon(null,null,null));
        dragonTypes.put("Fire dragon", new FireDragon(null,null,null));
        dragonTypes.put("Water dragon", new WaterDragon(null,null,null));
        dragonTypes.put("Earth dragon", new EarthDragon(null,null,null));
        dragonTypes.put("Metal dragon", new MetalDragon(null,null,null));
    }

    public void visit(Player visitor, int action){
        this.visitor = visitor;
        switch(action){
            case 1 -> {sellDragon();}
            case 2 -> {sellFood();}
        }
    }

    public void sellDragon(){
        printDragonMenu();
        sellDragonMenuAction(Menu.askPlayerNumber(
                true, "Which dragon do you want to buy?",dragonTypes.keySet().size(),1));

    }
    public void sellFood(){
        printFoodMenu();
        sellFoodMenuAction(Menu.askPlayerNumber(
                true, "Which food do you want to buy?", foodTypes.keySet().size(),1));
    }

    public void printDragonMenu(){
        System.out.println("***DRAGONS***");
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
    }

    private void sellDragonMenuAction(int action){

    }

    public void printFoodMenu(){
        int listCount = 1;
        for(var key: foodTypes.keySet()){
            System.out.println(listCount + ". " + key);
            listCount++;
        }
    }

    private void sellFoodMenuAction(int action){

    }

}
