package com.company;

public class MetalDragon extends Dragon {
    private int price = 2000;
    private String[] foodCanEat = new String[]{"metal"};
    private int maxAge = 20;
    private int maxBreedTimes = 10;

    private int age;
    private int breedTimes;
    public MetalDragon(String name, String gender, Player owner) {
        super(name, gender, owner, 100, true);
    }
    public void breed(){};
}
