package com.company.DragonSubClasses;

import com.company.*;

public class WaterDragon extends Dragon {

    public WaterDragon(String name, String gender, Player owner){
        super(name, gender, owner);
        foodCanEat = new String[]{"Fruit", "Meat"};
        price = 300;
        maxAge = 15;
        maxChildrenPerBreed = 3;
    }

    public void makeDragon(Player owner){
        String name = Printer.askPlayer(true, "Please name the new " + this.getClass().getSimpleName());
        String gender = ((int) (Math.random()*2) == 1 ? "MALE": "FEMALE");
        var newDragon = new WaterDragon(name,gender,owner);
        owner.addDragon(newDragon, false, 0);
    }
}
