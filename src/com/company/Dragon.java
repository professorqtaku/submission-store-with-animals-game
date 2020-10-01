package com.company;

public abstract class Dragon {
    protected String name;
    protected String gender;
    protected Player owner;
    protected int health;
    protected boolean isAlive;

    public Dragon(String name, String gender, Player owner, int health, boolean isAlive) {
        this.name = name;
        this.gender = gender;
        this.owner = owner;
        this.health = health;
        this.isAlive = isAlive;
    }

    public boolean living() {
        return isAlive;
    }

    public void die(){
        isAlive = false;
    }

    public void eat(Food food){ health += food.getAmount()*10;
    }

    // abstract like interface
    public abstract void breed();

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

    /*
    public void breedDragon(Dragon partner) {
        this.breedTimes++;
        partner.breedTimes++;
        int breedSuccessFul = (int) (Math.random() * 2);
        if (breedSuccessFul == 1) {
            System.out.printf("Congratulation! %s and %s got a baby dragon!", this.getName(), partner.getName());
            Dragon newDragon = newDragon(this.getClass().getSimpleName());
            //owner.buyDragon(newDragon);
        }
    }

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
