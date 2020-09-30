package com.company;

public class EarthDragon extends Dragon{
    public EarthDragon(String name, String gender, Player owner) {
        super(name, gender, new String[]{"metal", "fruit", "meat"}, 1000, 20, 5, owner);
    }
}
