package ru.nsu.fit.g18214.shatalov;

public class Worker {
  private int experience;
  private int efficiency;
  private boolean busy;
  private boolean ready = false;
  public Worker(int exp) {
    this.experience = exp;
    this.efficiency = (this.experience ^ 2) / 2;
    this.busy = false;
  }

  public boolean isBusy() {
    return this.busy;
  }

  public int getExperience() {
    return this.experience;
  }

  public int getEfficiency() {
    return this.efficiency;
  }

  public void takeOrder() {
    this.busy = true;
  }

  public void orderReady() {
    this.ready = true;
  }

}
