package com.company;

import java.io.*; // serialization/deserialization
import java.util.ArrayList;

// A helper class to serialize and deserialize data structure
// (objects, array list of objects etc)
public class Serializer {

    static public boolean serialize(String filePath, Object data) {
        try {
            var file = new FileOutputStream(filePath);
            var out = new ObjectOutputStream(file);
            out.writeObject(data);
            out.close();
            file.close();
            return true; // everything went fine
        }
        catch(Exception error){
            return false; // we couldn't complete the serialization
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
            return false; // we couldn't complete deserialization
        }
    }



}
