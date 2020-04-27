package com.tutorial.executors;

import java.util.concurrent.ExecutionException;
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

    public static void callables() {
        var executor = Executors.newFixedThreadPool(2);
        System.out.println(executor.getClass().getName());

        //Callables return values unlike runnable
        try {
            var future = executor.submit(() -> {
                LongTask.simulate();
                return 1;
            });

            System.out.println("Do some work... ");

            try {
                var result = future.get();
                System.out.println("Result = " + result);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        finally {
            executor.shutdown();
        }
    }
}
