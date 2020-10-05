package com.company;

public class WaterDragon extends Dragon {
    private String[] foodCanEat = new String[]{"Fruit", "Meat"};
    private int maxAge = 15;
    private int maxBreedTimes = 3;

    public WaterDragon(String name, String gender, Player owner) {
        super(name, gender, owner,300, 100,0,0);
    }

    public void makeDragon(Player owner){
        String name = Menu.askPlayer(true, "Please name the new " + this.getClass().getSimpleName());
        String gender = ((int) (Math.random()*2) == 1 ? "MALE": "FEMALE");
        var newDragon = new WaterDragon(name,gender,owner);
        owner.addDragon(newDragon, false);
    }
    public String[] getFoodCanEat(){return this.foodCanEat;}

}
