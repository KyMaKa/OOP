package ru.nsu.fit.g18214.shatalov;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class Warehouse {
  private int maxSpace;
  private int usedSpace = 0;
  private BlockingQueue<Order> storage;

  public Warehouse(int max) {
    this.maxSpace = max;
    this.storage = PizzaTime.storage;
  }

  public void placePackage(Order order) throws InterruptedException {
    if (hasFreeSpace()) {
      this.usedSpace++;
      this.storage.put(order);
    }
  }

  public Order takePackage() throws InterruptedException {
    Order order = this.storage.poll(5000, TimeUnit.MICROSECONDS);
    this.usedSpace--;
    return order;
  }

  public boolean hasFreeSpace() {
    return this.maxSpace != this.usedSpace;
  }
}
