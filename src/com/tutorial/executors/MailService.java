package com.tutorial.executors;

import java.util.concurrent.CompletableFuture;

public class MailService {
    public void send(){
        //Adds three seconds delay
        LongTask.simulate();
        System.out.println("A Mail was sent");
    }

    // if it was returning int it would be CompletableFuture<int>
    public CompletableFuture<Void> sendAsync(){
        return CompletableFuture.runAsync(()-> send());
    }
}
