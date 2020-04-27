package com.tutorial.collections;

import java.util.*;

public class SetDemo {
    public static void show(){
        Set<String> unique = new HashSet<>();
        unique.add("sky");
        unique.add("is");
        unique.add("blue");
        System.out.println(unique);
        //remove duplicates using a hashset
        Collection<String> collection = new ArrayList<>();
        Collections.addAll(collection, "a","b","b","a","c");
        Set<String> set= new HashSet<>(collection);
        System.out.println(set);

        //set operations
        Set<String> set1 = new HashSet<>(Arrays.asList("a","b","c"));
        Set<String> set2 = new HashSet<>(Arrays.asList("b","c","d"));
        //union
        set1.addAll(set2);
        System.out.println(set1);
        //intersection
        set1.retainAll(set2);
        System.out.println(set1);
        //difference
        set1.removeAll(set2);
        System.out.println(set1);
    }
}
