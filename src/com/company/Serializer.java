package com.company;

import java.io.*; // serialization/deserialization
import java.nio.file.Files;
import java.nio.file.Paths;


// A helper class to serialize and deserialize data structure
// (objects, array list of objects etc)
public class Serializer {

    static public void serialize(String filePath, Object data) {
        try {
            var file = new FileOutputStream(filePath);
            var out = new ObjectOutputStream(file);
            out.writeObject(data);
            out.close();
            file.close();
        }
        catch(Exception ignored){
        }
    }

    static public Object deserialize(String filePath){
        try {
            System.out.println("file exist: " + Files.exists(Paths.get(filePath)));
            var file = new FileInputStream(filePath);
            var in = new ObjectInputStream(file);
            var data = in.readObject();
            in.close();
            file.close();
            return data;
        }
        catch(Exception error){
            System.out.println("Load fail:" + error);
            return null;// we couldn't complete deserialization
        }
    }



}
