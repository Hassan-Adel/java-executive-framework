package com.tutorial.threads;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;

public class DownloadStatus {
  private int totalBytes;
  private AtomicInteger atomicTotalBytes = new AtomicInteger();

  public int getTotalBytesAdder() {
    return totalBytesAdder.intValue();
  }

  public void incrementTotalBytesAdder() {
    totalBytesAdder.increment();
  }

  private LongAdder totalBytesAdder = new LongAdder();
  private int totalFiles;
  public volatile boolean isDone;
  private Object totalBytesLock = new Object();
  private Object totalFilesLock = new Object();


  public int getTotalBytes() {
    return totalBytes;
  }

  public void incrementTotalBytes() {
    //every java object has a built in lock (monitor object)
    synchronized (totalBytesLock){
      this.totalBytes++;
    }
  }

  public void incrementAtomicTotalBytes() {
    //++i
    atomicTotalBytes.incrementAndGet();
  }

  public int getTotalFiles() {
    return totalFiles;
  }

  public AtomicInteger getAtomicTotalBytes() {
    return atomicTotalBytes;
  }

  public void incrementTotalFiles() {
    synchronized (totalFilesLock){
      this.totalFiles++;
    }
  }

    //same as using synchronized(this)
    public synchronized void incrementTotalFilesByTwo(){
        this.totalFiles+=2;
    }

  public boolean isDone() {
    return isDone;
  }

  public void done() {
    isDone = true;
  }

}
