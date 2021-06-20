package ru.nsu.fit.g18214.shatalov;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;

public class Sprite {
  public int positionX;
  public int positionY;

  public Sprite() {
    this.positionX = 0;
    this.positionY = 0;
  }

  public void setPosition(int x, int y) {
    this.positionX = x;
    this.positionY = y;
  }

  public void render(GraphicsContext gc) {
    //gc.setFill(this..COLOR);
    //gc.drawImage(this.image, this.positionX, this.positionY);
    gc.fillRect(this.positionX * Grid.size, this.positionY * Grid.size, Grid.size, Grid.size);
  }

  public Rectangle2D getBoundary() {
    return new Rectangle2D(this.positionX * Grid.size,
        this.positionY * Grid.size, Grid.size, Grid.size);
  }

  public boolean intersects(Sprite s) {
    return s.getBoundary().intersects(this.getBoundary());
  }
}
