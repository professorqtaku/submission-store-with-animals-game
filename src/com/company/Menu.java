package com.company;

public class Menu {
    public Menu(){

    }

    public void printWelcome(){
        print("*-*-*-* Welcome to \"MONOPOL WITH ANIMALS\"! *-*-*-*");
        print("Choose following:");
        print("1. New game");
        print("2. Load game");
    }

    public void printMenu(){
        print("Welcome");
    }
    private void print(String x){
        // print a string if it is not empty
        if(!x.equals("")){ System.out.println(x); }
    }



}
