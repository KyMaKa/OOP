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

  public void placePackage(Order order) throws InterruptedException {
    if (hasFreeSpace()) {
      this.usedSpace++;
      this.storage.put(order);
    }
  }

  public Order takePackage() throws InterruptedException {
    Order order = this.storage.take();
    this.usedSpace--;
    return order;
  }

  public boolean hasFreeSpace() {
    return this.maxSpace != this.usedSpace;
  }
}
