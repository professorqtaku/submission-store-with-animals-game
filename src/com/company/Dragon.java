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

    public void eat(){}

    public void looseHealth(int lostHealth){
        this.health -= lostHealth;
    }

    public boolean living(){
        if(health > 0){
            return true;
        }
        else{
            return false;
        }
    }

    // abstract like interface, forces the subclasses to have this class
    public void breed(Dragon partner){
        this.breedTimes++;
        partner.breedTimes++;
        int breedSuccessFul = (int) (Math.random() * 2);
        if (breedSuccessFul == 1) {
            System.out.printf("Congratulation! %s and %s got a baby dragon!\n", this.getName(), partner.getName());
            makeDragon(this.owner);
        }
    }

    public abstract void makeDragon(Player owner);

    public String getName() {
        return name;
    }

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

    /*

    public Dragon newDragon(String dragonClassName){
        String name = Menu.askPlayer(true, "Name the new " + dragonClassName + ".");
        String gender = ((int)(Math.random()*2) == 1 ? "male": "female");
        Dragon dragonToReturn;
        switch(dragonClassName){
            case "FireDragon" -> {dragonToReturn = new FireDragon(name,gender,this.owner);}
            case "WoodDragon" -> {dragonToReturn = new WoodDragon(name,gender,this.owner);}
            case "WaterDragon" -> {dragonToReturn = new WaterDragon(name,gender,this.owner);}
            case "EarthDragon" -> {dragonToReturn = new EarthDragon(name,gender,this.owner);}
            case "MetalDragon" -> {dragonToReturn = new MetalDragon(name,gender,this.owner);}
            default -> throw new IllegalStateException("Unexpected value: " + dragonClassName);
        }
        return dragonToReturn;
    }

     */

}
