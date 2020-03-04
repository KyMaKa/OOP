package ru.nsu.fit.g18214.shatalov;

import java.util.concurrent.BlockingQueue;

public class Worker implements Runnable {

  private BlockingQueue<Order> takenOrdersQueue;
  private BlockingQueue<Order> finishedOrdersQueue;
  private Warehouse warehouse;
  private int experience;
  private int efficiency;
  private boolean busy;
  private boolean ready = false;
  private Order order;
  private boolean button = false;

  public Worker(int exp, BlockingQueue<Order> takenOrdersQueue, BlockingQueue<Order> finishedOrdersQueue, Warehouse warehouse) {
    this.warehouse = warehouse;
    this.finishedOrdersQueue = finishedOrdersQueue;
    this.takenOrdersQueue = takenOrdersQueue;
    this.experience = exp;
    this.efficiency = (this.experience ^ 2) / 2;
    this.busy = false;
  }

  public boolean isBusy() {
    return this.busy;
  }

  public int getEfficiency() {
    return this.efficiency;
  }

  public void takeOrder() throws InterruptedException {
    this.busy = true;
    this.order = takenOrdersQueue.take();
  }

  public void orderReady() {
    this.ready = true;
  }

  public void free() {
    this.busy = false;
    this.ready = false;
  }

  public void run() {
    try {
      if (!isBusy() && !this.button) {
        this.button = true;
        takeOrder();
        this.button = false;
        this.busy = true;
        wait(getEfficiency() * 1000);
        orderReady();
        finishedOrdersQueue.put(order);
        warehouse.placePackage();
        free();
      }

    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

}
