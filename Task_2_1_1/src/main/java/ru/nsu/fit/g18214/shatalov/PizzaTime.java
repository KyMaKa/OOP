package ru.nsu.fit.g18214.shatalov;

import java.io.File;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class PizzaTime {
  static BlockingDeque<Order> orders = new LinkedBlockingDeque<>();
  static BlockingDeque<Order> storage = new LinkedBlockingDeque<>(10);
  static Warehouse warehouse = new Warehouse(10, storage);
  ArrayList<Worker> workersList = new ArrayList<Worker>();
  ArrayList<Order> ordersList = new ArrayList<>();
  ArrayList<Delivery> deliveryList = new ArrayList<>();
  static boolean buttonW = false;
  static boolean buttonD = false;

  File file = new File("stuff.json");
  ObjectMapper mapper = new ObjectMapper();
  ArrayNode arrayNode;
  ObjectNode objectNode = mapper.createObjectNode();

  public void openPizza() throws IOException {
    arrayNode = (ArrayNode) mapper.readTree(file);
    for (int i = 0; i < arrayNode.size(); i++) {
      Worker worker = new Worker(arrayNode.get(i).get("experience").asInt(), i);
      workersList.add(worker);
      Delivery delivery = new Delivery(i, 1, 1, storage);
      deliveryList.add(delivery);
    }
    for (int i = 1; i < 5; i++) {
      new Thread(workersList.get(i)).start();
    }
    for (int i = 0; i < 5; i++) {
      new Thread(deliveryList.get(i)).start();
    }

  }

  public void insertOrders() throws InterruptedException {
    for (int i = 0; i < 10; i++) {
      Order order = new Order(i);
      ordersList.add(order);
      orders.put(order);
    }
  }


  public static void main(String[] args) throws InterruptedException, IOException {
    PizzaTime pizzaTime = new PizzaTime();
    pizzaTime.insertOrders();
    while (!pizzaTime.ordersList.isEmpty()) {
      pizzaTime.openPizza();
    }
  }
}
