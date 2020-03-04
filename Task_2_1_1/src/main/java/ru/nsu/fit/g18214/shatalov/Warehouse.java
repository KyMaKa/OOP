package ru.nsu.fit.g18214.shatalov;

import java.util.concurrent.BlockingQueue;

public class Warehouse {
  private int maxSpace;
  private int usedSpace = 0;
  private BlockingQueue<Order> storage;

  public Warehouse(int max, BlockingQueue<Order> blockingQueue) {
    this.maxSpace = max;
    this.storage = blockingQueue;
  }

  public void placePackage() {
    if (hasFreeSpace()) {
      this.usedSpace++;
    }
  }

  public boolean hasFreeSpace() {
    return this.maxSpace != this.usedSpace;
  }
}
