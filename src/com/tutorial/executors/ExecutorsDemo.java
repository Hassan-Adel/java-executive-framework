package com.tutorial.executors;

import java.util.concurrent.Executors;

public class ExecutorsDemo {

    public static void show(){
        var executor = Executors.newFixedThreadPool(2);
        System.out.println(executor.getClass().getName());

        try {
            for (int i =0;i<10 ;i++){
                executor.submit(() -> {
                    System.out.println(Thread.currentThread().getName());
                });
            }
        }
        finally {
            executor.shutdown();
        }

    }
}
