package com.company;

import com.company.DragonSubClasses.*;
import com.company.FoodSubClasses.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        // DEADLINE 21/10 wednesday
        var dragon = new WoodDragon("Helly","female",null);
        dragon.gettingSick();
        System.out.println(dragon);
        //new Menu();
    }
}
