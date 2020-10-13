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
    protected int health = 100;
    protected int age = 0;
    protected String[] foodCanEat;
    protected int price;
    protected int maxAge;
    protected int maxChildrenPerBreed;
    protected boolean sick = false;

    public Dragon(String name, String gender, Player owner) {
        this.name = name;
        if(gender != null)
            setGender(gender);
        else this.gender = null;
        this.owner = owner;
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
        return ((health > 0 && age <= maxAge )|| sick);
    }

    public void mate(Dragon partner){
        if(this.getClass().equals(partner.getClass())) {
            if ((int) (Math.random() * 2) == 1) {
                int amountBabies = (int) (Math.random() * this.maxChildrenPerBreed) + 1;
                System.out.printf("Congratulation, breed successful! %s and %s got %d dragon(s)!\n",
                        this.name, partner.name, amountBabies);
                for(int i = 0; i< amountBabies; i++) {
                    System.out.println((i+1) + (i+1 == 1 ? "st" : (i+1 == 2 ? "nd" : (i+1 == 3 ? "rd" : "th"))) +
                            " dragon baby");
                    makeDragon(this.owner);
                }
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

    public boolean gettingSick(){
        sick = ((int) (Math.random() * 5) + 1 == 1);
        return sick;
    }

    public String[] getFoodCanEat(){
        return foodCanEat;
    }

    public int getPriceNow(){
        return (int) Math.round(price * health/100.0 * (age == 0 ? 1.0 : (1.0 - (double)age/maxAge)));
    }
}
