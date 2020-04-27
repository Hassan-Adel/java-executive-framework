package com.tutorial.collections;

import java.util.ArrayDeque;
import java.util.Queue;

public class QueueDemo {
    public  static void show(){
        Queue<String> queue = new ArrayDeque<>();
        queue.add("a");
        queue.add("b");
        //throws an exception if the queue is full
        queue.add("c");
        //returns false if the queue is full
        queue.offer("d");
        var front = queue.peek();
        System.out.println(front);
        //remove first item
        queue.poll();
    }
}
