package ru.nsu.fit.g18214.shatalov;

import static java.lang.Thread.sleep;
import static ru.nsu.fit.g18214.shatalov.PizzaTime.storage;
import static ru.nsu.fit.g18214.shatalov.PizzaTime.warehouse;

import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;

public class Delivery implements Runnable {

  private final int efficiency;
  private final int capacity;
  private int carried = 0;
  private boolean busy;
  private final int name;
  private Order order;
  //private final BlockingQueue<Order> storageQueue;
  private final ArrayList<Integer> packages;

  /**
   * Creates delivery guy unit with given parameters.
   * Calculates delivery guy efficiency.
   * @param name - name of delivery guy.
   * @param experience - is needed to calculate efficiency.
   * @param capacity - how many orders can fit in trunc.
   */
  public Delivery(int name, int experience, int capacity) {
    //this.storageQueue = storage;
    this.capacity = capacity;
    this.name = name;
    this.efficiency = (10) / experience;
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

  /*private void takePackage() throws InterruptedException {
    this.order = storageQueue.take();
    this.carried++;
  }*/

  /**
   * Clears and reset delivery guy state.
   */
  private void delivered() {
    this.busy = false;
    this.packages.clear();
    this.carried = 0;
  }

  /**
   * Starts thead (D.Guy starts working).
   * If PizzaTime shop is still working (PizzaTime.stop != true) ->
   * If button isn't pushed (other D.Duy isn't waiting for package to take) ->
   * -> pushes button and takes latest packages from warehouse queue ->
   * -> (if there is no - wait for it some time).
   * Amount of packages depend on D.guy trunc capacity.
   * D.Guy become busy (can't take packages).
   * Thread wait a certain time based on D.Guy efficiency and amount of orders in his trunc.
   * After this time packages counts as ready.
   * D.Guy state become free ->
   * -> (ready for packages) and thead restarts.
   */
  public void run() {
    try {
      if (!PizzaTime.stop) {
        if (!isBusy() && !PizzaTime.buttonD && this.carried != this.capacity) {
          PizzaTime.buttonD = true;
          int taken = 0;
          System.out.println("Delivery guy " + this.name + " pushed button");
          if (PizzaTime.stop && storage.isEmpty()) {
            return;
          }
          while (this.carried != this.capacity) {
            this.order = warehouse.takePackage();
            if (this.order != null) {
              this.carried++;
              taken++;
              this.packages.add(this.order.getId());
              System.out.println("Delivery guy " + this.name
                  + " took package number " + this.order.getId());
            } else {
              if (PizzaTime.stop) {
                break;
              }
            }
          }
          PizzaTime.buttonD = false;
          this.busy = true;
          for (int i = 0; i < taken; i++) {
            sleep(getEfficiency() * 700);
            System.out.println("Package number " + this.packages.get(i) + " delivered!");
          }
          delivered();
        }
        new Thread(this).start();
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
