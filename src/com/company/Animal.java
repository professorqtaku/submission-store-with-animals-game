package com.company;

public abstract class Animal {
    private String name;
    private String gender;
    private int health;
    private boolean isAlive;
    private String[] foodCanEat;
    private int price;
    private int maxAge;
    private int maxBreedTimes;

    public Animal(String name, String gender, int health, boolean isAlive, String[] foodCanEat, int price, int maxAge, int maxBreedTimes) {
        this.name = name;
        this.gender = gender;
        this.health = health;
        this.isAlive = isAlive;
        this.foodCanEat = foodCanEat;
        this.price = price;
        this.maxAge = maxAge;
        this.maxBreedTimes = maxBreedTimes;
    }

    public boolean living() {
        return isAlive;
    }

    public void die(){
        isAlive = false;
    }

    public void eat(Food food){
        health += food.getAmount()*10;
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
