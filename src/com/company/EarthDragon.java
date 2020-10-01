package com.company;

public class EarthDragon extends Dragon {
    private int price = 1000;
    private String[] foodCanEat = new String[]{"fruit", "meat", "metal"};
    private int maxAge = 20;
    private int maxBreedTimes = 5;

    public EarthDragon(String name, String gender, Player owner) {
        super(name, gender, owner, 100,0,0);
    }
    public void breed(Dragon partner){};
}
