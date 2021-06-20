package ru.nsu.fit.g18214.shatalov;

import javafx.scene.paint.Color;

import java.util.LinkedList;

public class Snake {
  public static final Color COLOR = Color.CORNSILK;
  public static final Color DEAD = Color.RED;
  private int length;
  private Sprite head;
  private int xVelocity;
  private int yVelocity;
  Grid grid;
  LinkedList<Sprite> tail;
  boolean dead = false;

  public Snake(int x, int y, Grid grid) {
    this.grid = grid;
    xVelocity = 0;
    yVelocity = -1;
    this.length = 1;
    tail = new LinkedList<>();
    head = new Sprite();
    head.setPosition(x,y);
    tail.add(head);
  }

  public void grow(int x, int y) {
    length++;
    add(x,y);
  }

  private void add(int x, int y) {
    Sprite sprite = new Sprite();
    sprite.setPosition(x,y);
    sprite = grid.checkAndFix(sprite);
    if (!tail.contains(sprite)) {
      tail.add(sprite);
      head = sprite;
    }
  }

  public static void headCollusion(Snake snake) {
    snake.tail.forEach((snakeTail) -> {
      if (snake.getHead().intersects(snakeTail) && snake.getHead() != snakeTail) {
        snake.snakeDied();
      }
    });
  }

  public int getxVelocity() {
    return this.xVelocity;
  }

  public int getyVelocity() {
    return this.yVelocity;
  }

  public void move() {
    add(head.positionX + this.xVelocity, head.positionY + this.yVelocity);
    tail.remove(0);
  }

  public Sprite getHead() {
    return head;
  }

  public void dirUp() {
    if (yVelocity == 1 && length > 1) return;
    this.xVelocity = 0;
    this.yVelocity = -1;
  }

  public void dirDown() {
    if (yVelocity == -1 && length > 1) return;
    this.xVelocity = 0;
    this.yVelocity = 1;
  }

  public void dirLeft() {
    if (xVelocity == 1 && length > 1) return;
    this.xVelocity = -1;
    this.yVelocity = 0;
  }

  public void dirRight() {
    if (xVelocity == -1 && length > 1) return;
    this.xVelocity = 1;
    this.yVelocity = 0;
  }

  public void snakeDied() {
    this.dead = true;
  }

  public boolean isDead() {
    return this.dead;
  }
}
