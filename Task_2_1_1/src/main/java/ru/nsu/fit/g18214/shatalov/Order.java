package ru.nsu.fit.g18214.shatalov;

import java.util.Random;

public class Order implements Runnable {

  private final int id;

  public Order(int id) {
    String order = "Pizza time!";
    this.id = id;
  }


  public int getId() {
    return this.id;
  }

  /**
   * Starts thread that generates orders over time.
   */
  public void run() {
    try {
      if (!PizzaTime.stop) {
        System.out.println("New order # " + this.id);
        PizzaTime.orders.put(this);
        Thread.sleep(4000);
        Order order = new Order(this.id + 1);
        new Thread(order).start();
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
