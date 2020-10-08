package com.company.DragonSubClasses;

import com.company.*;

public class WoodDragon extends Dragon {
    private final String[] foodCanEat = new String[]{"Fruit"};

    public WoodDragon(String name, String gender, Player owner) {
        super(name, gender, owner,100, 100,0,0, 5, 3);
    }

    public void makeDragon(Player owner){
        String name = Printer.askPlayer(true, "Please name the new " + this.getClass().getSimpleName());
        String gender = ((int) (Math.random()*2) == 1 ? "MALE": "FEMALE");
        WoodDragon newDragon = new WoodDragon(name,gender,owner);
        owner.addDragon(newDragon, false);
    }

    public String[] getFoodCanEat(){return this.foodCanEat;}

}
