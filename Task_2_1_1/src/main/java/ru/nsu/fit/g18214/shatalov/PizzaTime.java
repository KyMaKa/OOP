package ru.nsu.fit.g18214.shatalov;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class PizzaTime {
  static boolean stop = false;
  static BlockingDeque<Order> orders = new LinkedBlockingDeque<>();
  static BlockingDeque<Order> storage = new LinkedBlockingDeque<>(10);
  static Warehouse warehouse = new Warehouse(10);
  ArrayList<Worker> workersList = new ArrayList<>();
  //ArrayList<Order> ordersList = new ArrayList<>();
  ArrayList<Delivery> deliveryList = new ArrayList<>();

  static boolean buttonW = false;
  static boolean buttonD = false;

  File file = new File("stuff.json");
  ObjectMapper mapper = new ObjectMapper();

  JsonNode json;
  JsonNode bakers;
  JsonNode deliverys;

  /**
   * Reads json file with information about stuff.
   * Fills nodes with information from this file.
   * @throws IOException if required json file "stuff.json" not found.
   */
  public void read() throws IOException {
    json = mapper.readTree(file);
    bakers = json.get("Bakers");
    for (int i = 0; i < bakers.size(); i++) {
      Worker worker = new Worker(bakers.get(i).get("experience").asInt(), i);
      workersList.add(worker);
    }

    deliverys = json.get("Delivery");
    for (int i = 0; i < deliverys.size(); i++) {
      Delivery delivery = new Delivery(i, deliverys.get(i).get("experience").asInt(),
          deliverys.get(i).get("storage").asInt());
      deliveryList.add(delivery);
    }
  }

  /**
   * Starts threads one by one with parameters specified in json file.
   * @param bakers Json node with information about every baker.
   * @param deliverys Json node with information about every delivery guy.
   */
  private void startThreads(JsonNode bakers, JsonNode deliverys) {
    for (int i = 0; i < bakers.size(); i++) {
      new Thread(workersList.get(i)).start();
    }
    for (int i = 0; i < deliverys.size(); i++) {
      new Thread(deliveryList.get(i)).start();
    }
  }

  /**
   * Starts thead that generate orders.
   */
  private void createOrders() {
    new Thread(new Order(1)).start();
  }

  /**
   * Main method to start use all other methods.
   * @param args command line arguments.
   * @throws IOException --
   */
  public static void main(String[] args) throws IOException {
    PizzaTime pizzaTime = new PizzaTime();
    pizzaTime.createOrders();
    pizzaTime.read();
    pizzaTime.startThreads(pizzaTime.bakers, pizzaTime.deliverys);
    long t = System.currentTimeMillis();
    long end = t + 15000;
    while (System.currentTimeMillis() <= end) {
      stop = false;
    }
    stop = true;
  }
}
