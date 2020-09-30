package com.company;

public abstract class Animal {
    private String name;
    private String gender;
    public int health;
    private boolean isAlive;

    public Animal(String name, String gender, int health, boolean isAlive) {
        this.name = name;
        this.gender = gender;
        this.health = health;
        this.isAlive = isAlive;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void die(){
        isAlive = false;
    }
}
