package com.tutorial.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class CollectionsDemo {
    public static void show(){
        Collection<String> collection = new ArrayList<>();
        collection.add("a");
        collection.add("b");
        collection.add("c");
        //Add multiple elements
        Collections.addAll(collection, "d", "e", "f");
        int size = collection.size();
        collection.remove("e");
        collection.isEmpty();
        boolean x =  collection.contains("a");
        //Array of objects
        collection.toArray();
        //Array of strings
        String[] test = collection.toArray(new String[0]);
        System.out.println(collection);
        collection.clear();

        //Compare two collections
        Collection<String> other = new ArrayList<>();
        other.addAll(collection);
        //false by ref
        System.out.println(collection == other);
        //true by value
        System.out.println(collection.equals(other));


    }
}
