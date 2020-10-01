package com.company;

public class FireDragon extends Dragon {
    private int price = 500;
    private String[] foodCanEat = new String[]{"fruit", "meat"};
    private int maxAge = 15;
    private int maxBreedTimes = 5;

    public FireDragon(String name, String gender, Player owner) {
        super(name, gender, owner, 100, true,0,0);
    }
    public void breed(){};
}
