package com.tutorial.executors;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;

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

    public static void completableFuture() {
        //One of the implementations of the executor service interface
        //Returns the pool that is used by the completable future class
        //ForkJoinPool.commonPool();

        //Doesn't block the main thread (async)
        Runnable task = ()-> System.out.println("test task");
        var future = CompletableFuture.runAsync(task);
        var future2 = CompletableFuture.supplyAsync(()-> 1); //if task returns a value

        try {
            var result = future2.get();
            System.out.println("supplier result :" + result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
