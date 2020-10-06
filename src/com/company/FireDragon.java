package com.company;

public class FireDragon extends Dragon {
    private final String[] foodCanEat = new String[]{"Fruit", "Meat"};

    public FireDragon(String name, String gender, Player owner) {
        super(name, gender, owner, 500,100,0,0, 15, 5);
    }


    public void makeDragon(Player owner){
        String name = Menu.askPlayer(true, "Please name the new " + this.getClass().getSimpleName());
        String gender = ((int) (Math.random()*2) == 1 ? "MALE": "FEMALE");
        var newDragon = new FireDragon(name,gender,owner);
        owner.addDragon(newDragon, false);
    }
    public String[] getFoodCanEat(){return this.foodCanEat;}

}
