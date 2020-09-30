package com.company;

public class FireDragon extends Dragon{
    public FireDragon(String name, String gender, Player owner) {
        super(name, gender, new String[]{"fruit", "meat"}, 500, 15, 5, owner);
    }
}
