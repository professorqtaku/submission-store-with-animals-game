package com.company;

public abstract class Food {
    private int price;

    public Food(int price){
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}
