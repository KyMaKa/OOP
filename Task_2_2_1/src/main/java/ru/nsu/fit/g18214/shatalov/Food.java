package ru.nsu.fit.g18214.shatalov;


import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Food {
  double x;
  double y;
  Image food;

  public Food(double x, double y) {
    this.x = x;
    this.y = y;
    try {
      this.food = new Image(new FileInputStream(
          "src/resources/food.png"
      ));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  public Image getImage() {
    return this.food;
  }

  public double getX() {
    return this.x;
  }
  public double getY() {
    return this.y;
  }
}