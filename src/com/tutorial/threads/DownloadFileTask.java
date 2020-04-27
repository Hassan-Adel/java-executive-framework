package com.tutorial.threads;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DownloadFileTask implements Runnable {


    private DownloadStatus status;

    public DownloadFileTask(DownloadStatus status) {
        this.status = status;
    }

    public DownloadFileTask() {
        this.status = new DownloadStatus();
    }

    @Override
    public void run() {
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        System.out.println("Downloading a file : " + Thread.currentThread().getName() + " - " + timeStamp);
        for (int i = 0 ; i<10_0000; i++) {
            //check for an interruption request
            if (Thread.currentThread().isInterrupted())
                return;
            //status.incrementTotalBytes();
            status.incrementTotalBytesAdder();
        }
        status.done();

        synchronized (status){
            status.notifyAll();
        }

        System.out.println("Download completed: " + Thread.currentThread().getName());
    }

    public DownloadStatus getStatus() {
        return status;
    }
}
