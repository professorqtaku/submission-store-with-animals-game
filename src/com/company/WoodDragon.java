package com.company;

public class WoodDragon extends Dragon {
    private int price = 100;
    private String[] foodCanEat = new String[]{"fruit"};
    private int maxAge = 5;
    private int maxBreedTimes = 3;

    public WoodDragon(String name, String gender, Player owner) {
        super(name, gender, owner, 100,0,0);
    }
    public void breed(Dragon partner){
        this.breedTimes++;
        partner.breedTimes++;
        int breedSuccessFul = (int) (Math.random() * 2);
        if (breedSuccessFul == 1) {
            System.out.printf("Congratulation! %s and %s got a baby dragon!", this.getName(), partner.getName());
            WoodDragon newDragon = new WoodDragon(name,gender,this.owner);
            this.owner.buyDragon(newDragon);
        }
    }
}
