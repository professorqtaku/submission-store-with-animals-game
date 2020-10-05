package com.company;

public abstract class Dragon {
    protected String name;
    protected String gender;
    protected Player owner;
    private int price;
    protected int health;
    protected int age;
    protected int breedTimes;

    public Dragon(String name, String gender, Player owner, int price, int health, int age, int breedTimes) {
        this.name = name;
        if(gender != null)
            this.gender = gender.toUpperCase();
        else this.gender = null;
        this.owner = owner;
        this.price = price;
        this.health = health;
        this.age = age;
        this.breedTimes = breedTimes;
        if(owner != null)
            owner.addDragon(this, false); //make sure owner knows me (Le dragon)
        else this.owner = null;
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
        return health > 0;
    }

    public void breed(Dragon partner){
        if(this.getClass().getSimpleName().equals(partner.getClass().getSimpleName())) {
            this.breedTimes++;
            partner.breedTimes++;
            int breedSuccessFul = (int) (Math.random() * 2);
            if (breedSuccessFul == 1) {
                System.out.printf("Congratulation! %s and %s got a baby dragon!\n", this.name, partner.name);
                makeDragon(this.owner);
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
        if(owner == null){
            owner.removeDragon(this);
        }
        else {
            owner.addDragon(this, purchase);
        }
    }

    public int getPrice() {
        return price;
    }

    public abstract String[] getFoodCanEat();
}
