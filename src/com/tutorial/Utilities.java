package com.tutorial;

import com.tutorial.generics.GenericsList;
import com.tutorial.models.User;

public class Utilities {
    public static <T extends Comparable<T>> T max(T first , T second){
        return first.compareTo(second) > 0 ? first : second;
    }
    //public class CAP#1 extends User
    //public class Instructor extends User
    public static void printUsers(GenericsList<? extends User> users){
    }
}
