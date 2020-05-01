package com.tutorial.executors;

import java.sql.SQLOutput;
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

    public static void asynchronousAPI() {
        var service = new MailService();
        service.sendAsync();
        System.out.println("Hello World");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void runCodeOnCompletion() {
        var future = CompletableFuture.supplyAsync(()-> 1);
        future.thenRunAsync(()-> System.out.println("Done async ... Thread : " + Thread.currentThread().getName()));
        future.thenRun(()-> System.out.println("Done ... Thread : " + Thread.currentThread().getName()));
        //When you need to get the result of the completable future
        // result equals 1: future
        future.thenAccept((result)->{
            System.out.println(Thread.currentThread().getName());
            System.out.println(result);
        });
        future.thenAcceptAsync((result)->{
            System.out.println(Thread.currentThread().getName());
            System.out.println(result);
        });

        //pause main thread
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void HandleExeption() {
        var future = CompletableFuture.supplyAsync(()-> {
            System.out.println("Getting data..");
            throw new IllegalArgumentException();
        });

        try {
            //note that this returns a NEW completable future
            //exceptionally: maps a Throwable object to another type
            var dataOrDefault = future.exceptionally(ex -> 1).get();
            System.out.println(dataOrDefault);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            //get the actual cause
            e.getCause();
            e.printStackTrace();
        }
    }

    public static int toFahrenheit(int celsius){
        return (int) (celsius * 1.8) + 32;
    }

    public static void transformingCompletableFuture() {
        //get temp in celsius
        var future = CompletableFuture.supplyAsync(() -> 20);
        //convert it to fahrenheit after getting
       future
           .thenApply(ExecutorsDemo::toFahrenheit)
           .thenAccept(f -> System.out.println(f));
//        try {
//            //var result = future.thenApply(celsius -> toFahrenheit(celsius)).get();
//            var result = future.thenApply(ExecutorsDemo::toFahrenheit).get();
//            System.out.println(result);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
    }
}
