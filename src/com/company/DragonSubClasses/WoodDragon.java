package com.company.DragonSubClasses;

import com.company.*;

public class WoodDragon extends Dragon {
    public WoodDragon(String name, String gender, Player owner){
        super(name, gender, owner);
        foodCanEat = new String[]{"Fruit"};
        price = 100;
        maxAge = 5;
        maxChildrenPerBreed = 1;
    }

    public void makeDragon(Player owner){
        String gender = ((int) (Math.random()*2) == 1 ? "MALE": "FEMALE");
        String name = Printer.askPlayer(true,
                "Please name the new " + this.getClass().getSimpleName()+ " (" + gender + ")");
        WoodDragon newDragon = new WoodDragon(name,gender,owner);
        owner.addDragon(newDragon, false, 0);
    }
}
