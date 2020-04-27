package com.tutorial.collections;

import java.util.ArrayList;
import java.util.List;

public class ListDemo {
    public static void show(){
        List<String> list = new ArrayList<>();
        list.add("as");
        list.add("ab");
        list.add("c");
        list.add("d");
        list.add("e");
        list.add(0, "!");
        System.out.println(list.get(0));
        list.set(0, "r");
        System.out.println(list.get(0));
        list.remove(0);
        System.out.println(list.get(0));
        System.out.println(list.indexOf("ab"));
        System.out.println(list.lastIndexOf("a"));
        //from is inclusive , to is exclusive
        System.out.println(list.subList(0, 3));
        System.out.println(list);
    }
}
