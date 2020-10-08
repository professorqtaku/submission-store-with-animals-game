package com.company;

import java.io.Serializable;

public abstract class Food implements Serializable {
    protected int price = 0;

    public Food(){
    }

    public int getPrice() {
        return price;
    }
}
