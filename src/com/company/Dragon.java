package com.company;

import java.io.Serializable;

public abstract class Dragon implements Serializable {
    enum Gender{
        MALE,
        FEMALE
    }

    protected String name;
    protected Gender gender;
    protected Player owner;
    private int price;
    protected int health;
    protected int age;
    protected int breedTimes;
    protected final int maxAge;
    protected final int maxChildrenPerBreed;

    public Dragon(String name, String gender, Player owner, int price, int health, int age, int breedTimes, int maxAge, int maxChildrenPerBreed) {
        this.name = name;
        if(gender != null)
            setGender(gender);
        else this.gender = null;
        this.owner = owner;
        this.price = price;
        this.health = health;
        this.age = age;
        this.breedTimes = breedTimes;
        this.maxAge = maxAge;
        this.maxChildrenPerBreed = maxChildrenPerBreed;
        if(owner != null)
            owner.addDragon(this, false); //make sure owner knows me (Le dragon)
        else this.owner = null;
    }

    private void setGender(String gender){
        if(gender.equalsIgnoreCase("male")){
            this.gender = Gender.MALE;
        }
        else this.gender = Gender.FEMALE;
    }

    public void eat(String foodType, int foodQuantity){
        health += 10*foodQuantity;
        health = Math.min(health, 100);
        owner.consumeFood(foodType, foodQuantity);
    }

    public void reduceHealth(int healthToLose){
        this.health -= healthToLose;
    }

    public boolean living(){
        return (health > 0 && age <= maxAge);
    }

    public boolean canBreed(){
        return breedTimes < maxChildrenPerBreed;
    }

    public void breed(Dragon partner){
        if(this.getClass().equals(partner.getClass())) {
            this.breedTimes++;
            partner.breedTimes++;
            if ((int) (Math.random() * 2) == 1) {
                System.out.printf("Congratulation! %s and %s got a baby dragon!\n", this.name, partner.name);
                makeDragon(this.owner);
            }
            else{
                System.out.println(TextColour.RED + "Breed unsuccessful" + TextColour.RESET);
            }
        }
        else{
            System.out.println(TextColour.RED + "OBS! Dragons with different elements cannot breed. Breed unsuccessful!" + TextColour.RESET);
        }
    }

    // abstract like interface, forces the subclasses to have this class, without body
    public abstract void makeDragon(Player owner);

    public void changeOwner(Player owner, boolean purchase){
        if(this.owner.equals(owner)){
            return;
        }
        this.owner = owner;
        if(owner != null){
            owner.addDragon(this, purchase);
        }
        else {
            owner.removeDragon(this, false);
        }
    }

    public int getPrice() {
        return price;
    }

    public abstract String[] getFoodCanEat();
}
