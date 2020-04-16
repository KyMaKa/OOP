package ru.nsu.fit.g18214.shatalov;

import java.util.Random;

public class Order implements Runnable {

  private String order;
  private int id;

  public Order(int id) {
    this.order = "Pizza time!";
    this.id = id;
  }


  public int getId() {
    return this.id;
  }

  public void run() {
    int i = 1;
    try {
      Thread.sleep(new Random().nextInt((1000 - 1) + 1) + 1);
      System.out.println("New order # " + i);
      Order order = new Order(i++);
      PizzaTime.orders.put(order);

    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
