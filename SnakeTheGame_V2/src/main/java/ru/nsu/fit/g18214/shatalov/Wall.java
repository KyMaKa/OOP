package ru.nsu.fit.g18214.shatalov;

import javafx.scene.paint.Color;

import java.util.LinkedList;

public class Wall {
  int x;
  int y;
  int length;
  int dir; // 0 = vertical, 1 = horizontal
  Sprite wall;
  LinkedList<Wall> wallsInBetween = new LinkedList<>();
  public static final Color COLOR = Color.GRAY;

  public Wall(int x1, int y2, int dir, int length) {
    this.x = x1;
    this.y = y2;
    this.dir = dir;
    if (dir == 0 && (this.y + length <= 512/10)) {
      this.length = length;
    } else {
      if (dir == 0) {
        while (length + this.y > 512/10) {
          length--;
        }
        this.length = length;
      } else {
        if (this.x + length <= 512/10) {
          this.length = length;
        }
        else {
          while (length + this.x > 512/10) {
            length--;
          }
          this.length = length;
        }
      }
    }
    wall = new Sprite();
    wall.setPosition(x, y);
  }

  public int startX() {
    return this.x;
  }

  public int startY() {
    return this.y;
  }

  public Sprite getSpriteS() {
    return this.wall;
  }

  public int getLength() {
    return this.length;
  }

  public void changeLength(int l) {
    this.length = l;
  }

  public int getDir() {
    return this.dir;
  }

  public void addWall(Wall wall) {
    this.wallsInBetween.add(wall);
  }

  public LinkedList<Wall> getWalls() {
    return this.wallsInBetween;
  }
}
