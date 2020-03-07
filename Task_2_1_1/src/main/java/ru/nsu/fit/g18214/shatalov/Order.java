package ru.nsu.fit.g18214.shatalov;

public class Order {
  private String order;
  private int id;

  public Order(int id) {
    this.order = "Pizza time!";
    this.id = id;
  }

  public int getId() {
    return this.id;
  }

}
