package ru.nsu.fit.g18214.shatalov;

import java.util.concurrent.BlockingQueue;

import static java.lang.Thread.sleep;

public class Worker implements Runnable {

  private BlockingQueue<Order> takenOrdersQueue;
  private BlockingQueue<Order> finishedOrdersQueue;
  private Warehouse warehouse;
  private int experience;
  private int efficiency;
  private boolean busy;
  private boolean ready = false;
  private Order order;
  private int name;

  public Worker(int exp, int id, BlockingQueue<Order> takenOrdersQueue,
                BlockingQueue<Order> finishedOrdersQueue, Warehouse warehouse) {
    this.warehouse = warehouse;
    this.finishedOrdersQueue = finishedOrdersQueue;
    this.takenOrdersQueue = takenOrdersQueue;
    this.experience = exp;
    this.efficiency = (this.experience ^ 2) / 2;
    this.busy = false;
    this.name = id;
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

  public int getName() {
    return this.name;
  }

  public void run() {
    try {
      if (!isBusy() && !PizzaTime.buttonW) {
        PizzaTime.buttonW = true;
        System.out.println("Worker " + this.name + " pushed button");
        takeOrder();
        System.out.println("Worker " + this.name + " took order number " + this.order.getId());
        PizzaTime.buttonW = false;
        this.busy = true;
        sleep(getEfficiency() * 1000);
        orderReady();
        System.out.println("Order number " + this.order.getId() + " ready!");
        //finishedOrdersQueue.put(order);
        warehouse.placePackage(order);
        free();
      }

    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

}
