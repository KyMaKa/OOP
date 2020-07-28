package ru.nsu.fit.g18214.shatalov;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Sprite {
  private Image image;
  public int positionX;
  public int positionY;
  public int velocityX;
  public int velocityY;
  private int width;
  private int height;

  public Sprite() {
    this.positionX = 0;
    this.positionY = 0;
    this.velocityX = 0;
    this.velocityY = 0;
  }

  public void setPosition(int x, int y) {
    this.positionX = x;
    this.positionY = y;
  }

  public void setVelocity(int x, int y) {
    this.velocityX = x;
    this.velocityY = y;
  }

  public void addVelocity(int x, int y) {
    this.velocityX += x;
    this.velocityY += y;
  }

  public void update(int time) {
    this.positionX += velocityX * time;
    this.positionY += velocityY * time;
  }

  public void render(GraphicsContext gc) {
    gc.drawImage(this.image, this.positionX, this.positionY);
  }

  public Rectangle2D getBoundary() {
    return new Rectangle2D(this.positionX, this.positionY, this.width, this.height);
  }

  public boolean intersects(Sprite s) {
    return s.getBoundary().intersects(this.getBoundary());
  }
}
