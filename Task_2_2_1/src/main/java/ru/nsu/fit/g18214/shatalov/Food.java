package ru.nsu.fit.g18214.shatalov;


import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Food {
  int x;
  int y;
  Sprite food;
  public static final Color COLOR = Color.ROSYBROWN;

  public Food(int x, int y) throws FileNotFoundException {
    this.x = x;
    this.y = y;

    this.food = new Sprite();
    food.setPosition(x, y);
    //food.setImage("food.png");
  }

  public Sprite getSprite() {
    return this.food;
  }

  public int getX() {
    return this.x;
  }
  public int getY() {
    return this.y;
  }
}