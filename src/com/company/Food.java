package com.company;

public abstract class Food {
    private int price;
    public int amount;

    public Food(int price, int amount) {
        this.price = price;
        this.amount = amount;
    }

    public int getAmount(){
        return this.amount;
    }
}
