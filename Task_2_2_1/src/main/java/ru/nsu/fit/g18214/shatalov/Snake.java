package ru.nsu.fit.g18214.shatalov;

import java.util.LinkedList;

public class Snake {
  private int length;
  private Sprite head;
  private int xVelocity;
  private int yVelocity;
  Grid grid;
  LinkedList<Sprite> tail;

  public Snake(int x, int y, Grid grid) {
    this.grid = grid;
    xVelocity = 0;
    yVelocity = 0;
    this.length = 1;
    tail = new LinkedList<>();
    head = new Sprite();
    tail.add(head);
  }

  private void grow(int x, int y) {
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

  private void move() {
    add(head.positionX + xVelocity, head.positionY + yVelocity);
    tail.remove(0);
  }

  public void dirUp() {
    if (yVelocity == 1 && length > 1) return;
    xVelocity = 0;
    yVelocity = -1;
  }

  public void dirDown() {
    if (yVelocity == -1 && length > 1) return;
    xVelocity = 0;
    yVelocity = 1;
  }

  public void dirLeft() {
    if (xVelocity == 1 && length > 1) return;
    xVelocity = -1;
    yVelocity = 0;
  }

  public void dirRight() {
    if (xVelocity == -1 && length > 1) return;
    xVelocity = 1;
    yVelocity = 0;
  }
}
