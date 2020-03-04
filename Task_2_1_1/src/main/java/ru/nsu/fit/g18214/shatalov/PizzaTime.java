package ru.nsu.fit.g18214.shatalov;

import java.util.ArrayList;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class PizzaTime {
  BlockingDeque<Order> orders = new LinkedBlockingDeque<>();
  BlockingDeque<Order> storage = new LinkedBlockingDeque<>(10);
  Warehouse warehouse = new Warehouse(10, storage);
  ArrayList<Worker> workersList = new ArrayList<Worker>();
  ArrayList<Order> ordersList = new ArrayList<>();

  public void openPizza() {
    for (int i = 0; i < 5; i++) {
      Worker worker = new Worker(1, orders, storage, warehouse);
      workersList.add(worker);
    }
    for (int i = 1; i < 5; i++) {
      new Thread(workersList.get(i)).start();
    }

  }

  public void insertOrders() throws InterruptedException {
    for (int i = 0; i < 10; i++) {
      Order order = new Order();
      ordersList.add(order);
      orders.put(order);
    }
  }


  public static void main(String[] args) throws InterruptedException {
    PizzaTime pizzaTime = new PizzaTime();
    pizzaTime.insertOrders();
    while (!pizzaTime.ordersList.isEmpty()) {
      pizzaTime.openPizza();
    }
  }
}
