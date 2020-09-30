package com.company;

public class WaterDragon extends Dragon{
    public WaterDragon(String name, String gender, Player owner) {
        super(name, gender, new String[]{"fruit", "meat"}, 300, 15, 3, owner);
    }
}
