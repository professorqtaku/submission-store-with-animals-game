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
    public abstract void breed(Dragon partner);

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

    public void changeOwner(Player owner){
        if(this.owner.equals(owner)){
            return;
        }
        this.owner = owner;
        owner.buyDragon(this);
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
