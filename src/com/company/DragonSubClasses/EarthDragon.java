package com.company.DragonSubClasses;

import com.company.*;

public class EarthDragon extends Dragon {

    public EarthDragon(String name, String gender, Player owner){
        super(name, gender, owner);
        foodCanEat = new String[]{"Fruit", "Meat", "Metal"};
        price = 1000;
        maxAge = 20;
        maxChildrenPerBreed = 5;
    }

    public void makeDragon(Player owner){
        String name = Printer.askPlayer(true, "Please name the new " + this.getClass().getSimpleName());
        String gender = ((int) (Math.random()*2) == 1 ? "MALE": "FEMALE");
        var newDragon = new EarthDragon(name,gender,owner);
        owner.addDragon(newDragon, false);
    }
}
