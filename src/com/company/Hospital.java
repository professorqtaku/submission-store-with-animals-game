package com.company;

import java.util.LinkedHashMap;

public class Hospital extends Store{


    public Hospital(Game game){
        super(game);
    }

    public void visit(Player visitor, int action){
        this.visitor = visitor;
        switch(action){
            case 6 -> healDragon();
        }
    }

    private void healDragon(){

    }
}
