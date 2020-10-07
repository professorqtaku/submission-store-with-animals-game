package com.company;

import java.io.Serializable;

public abstract class Food implements Serializable {
    private int price;

    public Food(int price){
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}
