package com.company;

public class WoodDragon extends Dragon {
    private String[] foodCanEat = new String[]{"fruit"};
    private int maxAge = 5;
    private int maxBreedTimes = 3;

    public WoodDragon(String name, String gender, Player owner) {
        super(name, gender, owner,100, 100,0,0);
    }

    public void makeDragon(Player owner){
        String name = Menu.askPlayer(true, "Please name the new " + this.getClass().getSimpleName());
        String gender = ((int) (Math.random()*2) == 1 ? "MALE": "FEMALE");
        WoodDragon newDragon = new WoodDragon(name,gender,owner);
        owner.addDragon(newDragon);
    }

    public String[] getFoodCanEat(){return this.foodCanEat;}

}
