package com.company.DragonSubClasses;

import com.company.*;

public class FireDragon extends Dragon {

    public FireDragon(String name, String gender, Player owner){
        super(name, gender, owner);
        foodCanEat = new String[]{"Fruit", "Meat"};
        price = 500;
        maxAge = 15;
        maxChildrenPerBreed = 5;
    }


    public void makeDragon(Player owner){
        String name = Printer.askPlayer(true, "Please name the new " + this.getClass().getSimpleName());
        String gender = ((int) (Math.random()*2) == 1 ? "MALE": "FEMALE");
        var newDragon = new FireDragon(name,gender,owner);
        owner.addDragon(newDragon, false);
    }
}
