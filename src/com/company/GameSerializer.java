package com.company;

import java.io.*; // serialization/deserialization


// A helper class to serialize and deserialize data structure
// (objects, array list of objects etc)
public class GameSerializer {

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
            var file = new FileInputStream(filePath);
            var in = new ObjectInputStream(file);
            var data = in.readObject();
            in.close();
            file.close();
            return data;
        }
        catch(Exception error){
            return null;// we couldn't complete deserialization
        }
    }



}
