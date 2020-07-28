package ru.nsu.fit.g18214.shatalov;

public class Grid {
  public static final int size = 10;

  private final int rows;
  private final int cols;

  public Grid(int width, int height) {
    this.rows = (int) width / size;
    this.cols = (int) height / size;
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
    if (x >= rows) x = 0;
    if (y >= cols) y = 0;
    if (x < 0) x = rows - 1;
    if (y < 0) y = cols - 1;
    Sprite sprite = new Sprite();
    sprite.setPosition(x,y);
    return sprite;
  }

}
