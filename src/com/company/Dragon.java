package com.company;

public abstract class Dragon {
    protected String name;
    protected String gender;
    protected Player owner;
    protected int health;
    protected int age;
    protected int breedTimes;

    public Dragon(String name, String gender, Player owner, int health, int age, int breedTimes) {
        this.name = name;
        this.gender = gender;
        this.owner = owner;
        this.health = health;
        this.age = age;
        this.breedTimes = breedTimes;
        owner.addDragon(this); //make sure owner knows me (Le dragon)
    }

    public void eat(Food food, int foodQuantity){
        health += 10*foodQuantity;
        owner.consumeFood(food.getClass().getSimpleName(), foodQuantity);
    }

    public void reduceHealth(int healthToLose){
        this.health -= healthToLose;
    }

    public boolean living(){
        return health > 0;
    }

    public void breed(Dragon partner){
        this.breedTimes++;
        partner.breedTimes++;
        int breedSuccessFul = (int) (Math.random() * 2);
        if (breedSuccessFul == 1) {
            System.out.printf("Congratulation! %s and %s got a baby dragon!\n", this.name, partner.name);
            makeDragon(this.owner);
        }
    }

    // abstract like interface, forces the subclasses to have this class, without body
    public abstract void makeDragon(Player owner);

    public void changeOwner(Player owner){
        if(this.owner.equals(owner)){
            return;
        }
        this.owner = owner;
        if(owner == null){
            owner.removeDragon(this);
        }
        else {
            owner.addDragon(this);
        }
    }

}
