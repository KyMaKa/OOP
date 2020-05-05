package ru.nsu.fit.g18214.shatalov;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;
import static ru.nsu.fit.g18214.shatalov.PizzaTime.orders;
import static ru.nsu.fit.g18214.shatalov.PizzaTime.warehouse;

public class Worker implements Runnable {

  private BlockingQueue<Order> takenOrdersQueue;
  private int experience;
  private int efficiency;
  private boolean busy;
  private boolean ready = false;
  private Order order;
  private int name;

  public Worker(int exp, int id) {
    this.takenOrdersQueue = orders;
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
    this.order = takenOrdersQueue.poll(5000, TimeUnit.MILLISECONDS);
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
      if (!PizzaTime.stop) {
        if (!isBusy() && !PizzaTime.buttonW) {
          PizzaTime.buttonW = true;
          System.out.println("Worker " + this.name + " pushed button");
          if (PizzaTime.stop && takenOrdersQueue.isEmpty()) {
            return;
          }
          takeOrder();
          if (this.order != null) {
            System.out.println("Worker " + this.name + " took order number "
                + this.order.getId());
            PizzaTime.buttonW = false;
            this.busy = true;
            sleep(getEfficiency() * 1000);
            orderReady();
            System.out.println("Order number " + this.order.getId() + " ready!");
            //finishedOrdersQueue.put(order);
            warehouse.placePackage(order);
            free();
          }
        }
        new Thread(this).start();
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

}
