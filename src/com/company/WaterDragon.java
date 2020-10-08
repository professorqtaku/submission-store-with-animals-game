package com.company;

public class WaterDragon extends Dragon {
    private final String[] foodCanEat = new String[]{"Fruit", "Meat"};

    public WaterDragon(String name, String gender, Player owner) {
        super(name, gender, owner,300, 100,0,0 ,15, 3);
    }

    public void makeDragon(Player owner){
        String name = Printer.askPlayer(true, "Please name the new " + this.getClass().getSimpleName());
        String gender = ((int) (Math.random()*2) == 1 ? "MALE": "FEMALE");
        var newDragon = new WaterDragon(name,gender,owner);
        owner.addDragon(newDragon, false);
    }
    public String[] getFoodCanEat(){return this.foodCanEat;}

}
