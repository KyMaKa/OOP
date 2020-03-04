package ru.nsu.fit.g18214.shatalov;

import java.util.ArrayList;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class PizzaTime {
  BlockingDeque<Order> orders = new LinkedBlockingDeque<>();
  BlockingDeque<Order> storage = new LinkedBlockingDeque<>(10);
  Warehouse warehouse = new Warehouse(10, storage);
  ArrayList<Worker> arrayList = new ArrayList<Worker>();
  Worker worker = new Worker(1, orders, storage, warehouse);
  boolean button = false;
  
}
