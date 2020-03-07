package ru.nsu.fit.g18214.shatalov;

import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;

import static java.lang.Thread.sleep;

public class Delivery implements Runnable {
  private int experience;
  private int efficiency;
  private int capacity;
  private int carried = 0;
  private boolean busy;
  private int name;
  private Order order;
  private BlockingQueue<Order> storageQueue;
  private ArrayList<Integer> packages;
  private Warehouse warehouse;

  public Delivery(int name, int experience, int capacity, BlockingQueue<Order> storageQueue, Warehouse warehouse) {
    this.storageQueue = storageQueue;
    this.capacity = capacity;
    this.experience = experience;
    this.name = name;
    this.efficiency = (10) / this.experience;
    this.warehouse = warehouse;
    this.packages = new ArrayList<>();
  }

  public int getEfficiency() {
    return this.efficiency;
  }

  public int getCapacity() {
    return this.capacity;
  }

  private boolean isBusy() {
    return this.busy;
  }

  private void takePackage() throws InterruptedException {
    this.order = storageQueue.take();
    this.carried++;
  }

  private void delivered() {
    this.busy = false;
    this.packages.clear();
    this.carried = 0;
  }

  public void run() {
    try {
      if (!isBusy() && !PizzaTime.buttonD && this.carried != this.capacity) {
        PizzaTime.buttonD = true;
        int taken = 0;
        System.out.println("Delivery guy " + this.name + " pushed button");
        while(this.carried != this.capacity) {
          this. order = warehouse.takePackage();
          this.carried++;
          taken++;
          this.packages.add(this.order.getId());
          System.out.println("Delivery guy " + this.name + " took package number " + this.order.getId());
        }
        PizzaTime.buttonD = false;
        this.busy = true;
        for (int i = 0; i < taken; i++) {
          sleep(getEfficiency() * 700);
          System.out.println("Package number " + this.packages.get(i) + " delivered!");
        }
        delivered();
      }

    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
