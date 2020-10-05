package com.company;

public class MetalDragon extends Dragon {
    private String[] foodCanEat = new String[]{"Metal"};
    private int maxAge = 20;
    private int maxBreedTimes = 10;

    public MetalDragon(String name, String gender, Player owner) {
        super(name, gender, owner, 2000,100,0,0);
    }

    public void makeDragon(Player owner){
        String name = Menu.askPlayer(true, "Please name the new " + this.getClass().getSimpleName());
        String gender = ((int) (Math.random()*2) == 1 ? "MALE": "FEMALE");
        var newDragon = new MetalDragon(name,gender,owner);
        owner.addDragon(newDragon, false);
    }

    public String[] getFoodCanEat(){return this.foodCanEat;}

}
