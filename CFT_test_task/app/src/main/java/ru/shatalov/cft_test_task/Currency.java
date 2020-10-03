package ru.shatalov.cft_test_task;

public class Currency {

  private int numCode;
  private String charCode;
  private int nominal;
  private String name;
  private double value;

  public Currency() {
  }

  /*public Currency(String name, double value) {
    this.name = name;
    this.value = value;
  }*/

  public int getNumCode() {
    return this.numCode;
  }

  public void setNumCode(int code) {
    this.numCode = code;
  }

  public String getCharCode() {
    return this.charCode;
  }

  public void setCharCode(String code) {
    this.charCode = code;
  }

  public int getNominal() {
    return this.nominal;
  }

  public void setNominal(int nominal) {
    this.nominal = nominal;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public double getValue() {
    return this.value;
  }

  public void setValue(double value) {
    this.value = value;
  }
}
