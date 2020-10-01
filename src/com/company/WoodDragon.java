package com.company;

public class WoodDragon extends Dragon {
    private int price = 100;
    private String[] foodCanEat = new String[]{"fruit"};
    private int maxAge = 5;
    private int maxBreedTimes = 3;

    public WoodDragon(String name, String gender, Player owner) {
        super(name, gender, owner, 100, true,0,0);
    }
    public void breed(){};
}
