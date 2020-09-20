package ru.nsu.fit.g18214.shatalov;

import javafx.scene.paint.Color;

public class Wall {
  int xs;
  int xe;
  int ys;
  int ye;
  int length;
  Sprite wallS;
  Sprite wallE;
  public static final Color COLOR = Color.GRAY;

  /*public Wall(int x1, int y2) {
    this.xs = x1;
    this.ye = y2;
  }*/

  public void setEnd(int x, int y) {
    this.xe = x;
    this.ye = y;
    wallE = new Sprite();
    wallE.setPosition(xe, ye);
    this.length = this.xe - this.xs + this.ye - this.ys - 1;
  }

  public void setStart(int x, int y) {
    this.xs = x;
    this.ys = y;
    wallS = new Sprite();
    wallS.setPosition(xs, ys);
  }

  public int startX() {
    return this.xs;
  }

  public int endX() {
    return this.xe;
  }

  public int startY() {
    return this.ys;
  }

  public int endY() {
    return this.ye;
  }

  public Sprite getSpriteS() {
    return wallS;
  }

  public Sprite getSpriteE() {
    return wallE;
  }

  public int getLength() {
    return this.length;
  }

}
