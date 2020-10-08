package com.company.DragonSubClasses;

import com.company.*;

public class EarthDragon extends Dragon {
    private final String[] foodCanEat = new String[]{"Fruit", "Meat", "Metal"};

    public EarthDragon(String name, String gender, Player owner){
        super(name, gender, owner,1000, 100,0,0, 20, 5);
    }

    public void makeDragon(Player owner){
        String name = Printer.askPlayer(true, "Please name the new " + this.getClass().getSimpleName());
        String gender = ((int) (Math.random()*2) == 1 ? "MALE": "FEMALE");
        var newDragon = new EarthDragon(name,gender,owner);
        owner.addDragon(newDragon, false);
    }

    public String[] getFoodCanEat(){return this.foodCanEat;}
}
