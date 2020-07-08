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
  /*BlockingDeque<Order> storage;*/
  static Warehouse warehouse;
  ArrayList<Worker> workersList = new ArrayList<>();
  ArrayList<Delivery> deliveryList = new ArrayList<>();

  static boolean buttonW = false;
  static boolean buttonD = false;

  ObjectMapper mapper = new ObjectMapper();
  JsonNode bakers;
  JsonNode deliverys;
  JsonNode warehouseSize;
  /**
   * Reads json file with information about stuff.
   * Fills jsonNodes with information from this file.
   * @throws IOException if required json file "stuff.json" not found.
   * @throws NullPointerException if file missing some required information.
   */
  public void readAndFill(File file) throws IOException {
    JsonNode json;
    json = mapper.readTree(file);
    bakers = json.get("Bakers");
    if (bakers == null) {
      throw new NullPointerException("No info about bakers in file");
    }
    for (int i = 0; i < bakers.size(); i++) {
      Worker worker = new Worker(bakers.get(i).get("experience").asInt(), i);
      workersList.add(worker);
    }

    deliverys = json.get("Delivery");
    if (deliverys == null) {
      throw new NullPointerException("No info about delivery in file");
    }
    for (int i = 0; i < deliverys.size(); i++) {
      Delivery delivery = new Delivery(i, deliverys.get(i).get("experience").asInt(),
          deliverys.get(i).get("storage").asInt());
      deliveryList.add(delivery);
    }

    warehouseSize = json.get("Warehouse");
    if (warehouseSize == null) {
      throw new NullPointerException("No info about warehouse size in file");
    }
  }

  /**
   * Starts threads one by one with employees parameters from json nodes.
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
   * Starts thead that generates orders.
   */
  private void createOrders() {
    new Thread(new Order(1)).start();
  }

  private void setWarehouse() {
    warehouse = new Warehouse(warehouseSize.get(0).get("maxSpace").asInt());
  }

  /**
   * Main method to start use all other methods.
   * Program stops after 15 seconds like PizzaTime shop work hours is ended.
   * All already received orders will be proceed and delivered.
   * No new orders will be taken.
   * @param args command line arguments.
   * @throws IOException if there is no file or file cannot be read.
   */
  public static void main(String[] args) throws IOException {
    File file = new File("stuff.json");
    PizzaTime pizzaTime = new PizzaTime();
    pizzaTime.setWarehouse();
    pizzaTime.createOrders();
    pizzaTime.readAndFill(file);
    pizzaTime.startThreads(pizzaTime.bakers, pizzaTime.deliverys);
    long t = System.currentTimeMillis();
    long end = t + 15000;
    while (System.currentTimeMillis() <= end) {
      stop = false;
    }
    stop = true;
    System.out.println("=============================================");
    System.out.println("No more orders will be taken.");
    System.out.println("=============================================");
  }
}
