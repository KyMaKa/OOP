package ru.nsu.fit.g18214.shatalov;

import javafx.scene.paint.Color;

public class Grid {
  public static final int size = 10;

  private final int rows;
  private final int cols;

  public static final Color COLOR = new Color(0.1, 0.1, 0.1, 1);

  public Grid(int width, int height) {
    this.cols = (int) width / size;
    this.rows = (int) height / size;
  }

  public int getRows() {
    return this.rows;
  }

  public int getCols() {
    return this.cols;
  }

  public Sprite checkAndFix(Sprite point) {
    int x = point.positionX;
    int y = point.positionY;
    if (y >= rows) y = 0;
    if (x >= cols) x = 0;
    if (x < 0) x = cols - 1;
    if (y < 0) y = rows - 1;
    Sprite sprite = new Sprite();
    sprite.setPosition(x,y);
    return sprite;
  }

}
