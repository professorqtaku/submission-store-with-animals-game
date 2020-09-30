package com.company;

public class Dragon extends Animal{
    private Player owner;
    public Dragon(String name, String gender, String[] foodCanEat, int price, int maxAge, int maxBreedTimes, Player owner) {
        super(name, gender, 100, true, foodCanEat, price, maxAge, maxBreedTimes, 0);
        this.owner = owner;
    }

    public void breed(Dragon partner) {
        this.breedTimes++;
        partner.breedTimes++;
        int breedSuccessFul = (int) (Math.random() * 2);
        if (breedSuccessFul == 1) {
            System.out.printf("Congratulation! %s and %s got a baby dragon!", this.getName(), partner.getName());
             Dragon newDragon = newDragon(this.getClass().getSimpleName());
             owner.buyDragon(newDragon);
        }
    }

    public Dragon newDragon(String dragonClassName){
        String name = Menu.askPlayer(true, "Name the new " + dragonClassName + ".");
        String gender = ((int)(Math.random()*2) == 1 ? "male": "female");
        Dragon dragonToReturn;
        switch(dragonClassName){
            case "FireDragon" -> {dragonToReturn = new FireDragon(name,gender,this.owner);}
            case "WoodDragon" -> {dragonToReturn = new WoodDragon(name,gender,this.owner);}
            case "WaterDragon" -> {dragonToReturn = new WaterDragon(name,gender,this.owner);}
            case "EarthDragon" -> {dragonToReturn = new EarthDragon(name,gender,this.owner);}
            case "MetalDragon" -> {dragonToReturn = new MetalDragon(name,gender,this.owner);}
            default -> throw new IllegalStateException("Unexpected value: " + dragonClassName);
        }
        return dragonToReturn;
    }
}
