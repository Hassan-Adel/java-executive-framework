package com.tutorial.models;

public class User implements Comparable<User> {
    int points;

    public User(int points) {
        this.points = points;
    }

    @Override
    public int compareTo(User other) {
        //this > other --> +ve
        //this < other.points --> -ve
        //this == other.points --> 0
        return this.points - other.points;

//        if(points < other.points)
//            return -1;
//        if(this.points < other.points)
//            return 1;
//        return 0;
    }
}
