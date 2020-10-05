package com.company;

public class FireDragon extends Dragon {
    private String[] foodCanEat = new String[]{"fruit", "meat"};
    private int maxAge = 15;
    private int maxBreedTimes = 5;

    public FireDragon(String name, String gender, Player owner) {
        super(name, gender, owner, 500,100,0,0);
    }


    public void makeDragon(Player owner){
        String name = Menu.askPlayer(true, "Please name the new " + this.getClass().getSimpleName());
        String gender = ((int) (Math.random()*2) == 1 ? "MALE": "FEMALE");
        var newDragon = new FireDragon(name,gender,owner);
        owner.addDragon(newDragon);
    }
    public String[] getFoodCanEat(){return this.foodCanEat;}

}
