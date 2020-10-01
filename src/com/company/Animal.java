package com.company;

public abstract class Animal {
    protected String name;
    protected String gender;
    protected int health;
    protected boolean isAlive;
    protected String[] foodCanEat;
    protected int price;
    protected int maxAge;
    protected int maxBreedTimes;
    protected int breedTimes;

    public Animal(String name, String gender, int health, boolean isAlive, String[] foodCanEat, int price, int maxAge, int maxBreedTimes, int breedTimes) {
        this.name = name;
        this.gender = gender;
        this.health = health;
        this.isAlive = isAlive;
        this.foodCanEat = foodCanEat;
        this.price = price;
        this.maxAge = maxAge;
        this.maxBreedTimes = maxBreedTimes;
        this.breedTimes = breedTimes;
    }

    public boolean living() {
        return isAlive;
    }

    public void die(){
        isAlive = false;
    }

    public void eat(Food food){ health += food.getAmount()*10;
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

}
