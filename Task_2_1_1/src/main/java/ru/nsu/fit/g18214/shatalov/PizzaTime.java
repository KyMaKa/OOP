package ru.nsu.fit.g18214.shatalov;

import java.io.File;
import com.fasterxml.jackson.databind.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
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

  public void openPizza() throws IOException {
    JsonNode json = mapper.readTree(file);
    JsonNode bakers = json.get("Bakers");
    for (int i = 0; i < bakers.size(); i++) {
      Worker worker = new Worker(bakers.get(i).get("experience").asInt(), i);
      workersList.add(worker);
    }
    JsonNode deliverys = json.get("Delivery");
    for (int i = 0; i < deliverys.size(); i++) {
      Delivery delivery = new Delivery(i, deliverys.get(i).get("experience").asInt(),
          deliverys.get(i).get("storage").asInt());
      deliveryList.add(delivery);
    }
    for (int i = 0; i < bakers.size(); i++) {
      new Thread(workersList.get(i)).start();
    }
    for (int i = 0; i < deliverys.size(); i++) {
      new Thread(deliveryList.get(i)).start();
    }

  }

  public void createOrders() {
    new Thread(new Order(1)).start();
  }


  public static void main(String[] args) throws InterruptedException, IOException {
    PizzaTime pizzaTime = new PizzaTime();
    pizzaTime.createOrders();
    long t = System.currentTimeMillis();
    long end = t + 150000;
    while (System.currentTimeMillis() < end) {
      pizzaTime.openPizza();
    }
  }
}
