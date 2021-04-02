package ru.nsu.fit.g18214.shatalov;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

public class Warehouse {
  private int maxSpace;
  private int usedSpace = 0;
  static BlockingQueue<Order> storage;

  public Warehouse(int max) {
    this.maxSpace = max;
    this.storage = new LinkedBlockingDeque<>(max);
  }

  /**
   * Places package in queue and increases counter of used space (how many items in queue).
   * @param order package to place. S
   * @throws InterruptedException if interrupted while waiting.
   */
  public void placePackage(Order order) throws InterruptedException {
    if (hasFreeSpace()) {
      this.usedSpace++;
      this.storage.put(order);
    }
  }

  /**
   * Takes package from queue.
   * If queue is empty - wait 5 seconds and then returns null.
   * @return taken package or null if queue if empty.
   * @throws InterruptedException if interrupted while waiting.
   */
  public Order takePackage() throws InterruptedException {
    Order order = this.storage.poll(5000, TimeUnit.MICROSECONDS);
    if (order != null) {
      this.usedSpace--;
    }
    return order;
  }

  public boolean isEmoty() {
    return this.storage.isEmpty();
  }

  public boolean hasFreeSpace() {
    return this.maxSpace != this.usedSpace;
  }
}
