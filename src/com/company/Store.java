package com.company;

public class Store {
    private Player visitor;

    public void visit(Player visitor, int action){
        this.visitor = visitor;
        switch(action){
            case 1 -> {sellDragon();}
            case 2 -> {sellFood();}
        }
    }

    public void sellDragon(){

    }
    public void sellFood(){

    }

}
