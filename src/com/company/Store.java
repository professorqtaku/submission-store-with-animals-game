package com.company;

import java.util.HashMap;

public class Store {
    private Player visitor;
    private HashMap<String, Food> foodTypes; // key = name of food, value = instance of food sub class

    public Store(){
        foodTypes = new HashMap<String, Food>();
        //initialisera matsorter i store
        foodTypes.put("fruit", new Fruit());
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
    }
    public void sellFood(){
        printFoodMenu();
    }

    private void printDragonMenu(){

    }

    private void printFoodMenu(){

    }

}
