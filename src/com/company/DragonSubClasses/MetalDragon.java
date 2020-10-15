package com.company.DragonSubClasses;

import com.company.*;

public class MetalDragon extends Dragon {

    public MetalDragon(String name, String gender, Player owner){
        super(name, gender, owner);
        foodCanEat = new String[]{"Metal"};
        price = 2000;
        maxAge = 20;
        maxChildrenPerBreed = 10;
    }

    public void makeDragon(Player owner){
        String name = Printer.askPlayer(true, "Please name the new " + this.getClass().getSimpleName());
        String gender = ((int) (Math.random()*2) == 1 ? "MALE": "FEMALE");
        var newDragon = new MetalDragon(name,gender,owner);
        owner.addDragon(newDragon, false,0);
    }
}
