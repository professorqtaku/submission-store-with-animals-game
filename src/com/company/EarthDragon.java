package com.company;

public class EarthDragon extends Dragon {
    private String[] foodCanEat = new String[]{"fruit", "meat", "metal"};
    private int maxAge = 20;
    private int maxBreedTimes = 5;

    public EarthDragon(String name, String gender, Player owner){
        super(name, gender, owner,1000, 100,0,0);
    }

    public void makeDragon(Player owner){
        String name = Menu.askPlayer(true, "Please name the new " + this.getClass().getSimpleName());
        String gender = ((int) (Math.random()*2) == 1 ? "MALE": "FEMALE");
        var newDragon = new EarthDragon(name,gender,owner);
        owner.addDragon(newDragon);
    }

    public String[] getFoodCanEat(){return this.foodCanEat;}
}
