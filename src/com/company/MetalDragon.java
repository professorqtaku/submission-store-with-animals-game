package com.company;

public class MetalDragon extends Dragon{
    public MetalDragon(String name, String gender, Player owner) {
        super(name, gender, new String[]{"metal"}, 1000, 20, 5, owner);
    }
}
