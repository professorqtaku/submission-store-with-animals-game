package com.company;

public class WaterDragon extends Dragon {
    private int price = 300;
    private String[] foodCanEat = new String[]{"fruit", "meat"};
    private int maxAge = 15;
    private int maxBreedTimes = 3;

    public WaterDragon(String name, String gender, Player owner) {
        super(name, gender, owner, 100, true,0,0);
    }
    public void breed(Dragon partner){};
}
