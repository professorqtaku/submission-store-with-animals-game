package com.company;

public class FireDragon extends Dragon {
    private int price = 500;
    private String[] foodCanEat = new String[]{"fruit", "meat"};
    private int maxAge = 15;
    private int maxBreedTimes = 5;

    private int age;
    private int breedTimes;
    public FireDragon(String name, String gender, Player owner) {
        super(name, gender, owner, 100, true);
    }
    public void breed(){};
}
