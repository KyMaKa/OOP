package ru.nsu.fit.g18214.shatalov;

import javafx.scene.paint.Color;

import java.util.LinkedList;
import java.util.Random;

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

  public static void createWall(Grid grid, LinkedList<Wall> walls) { //in the way that walls don't cross each other
    Random r = new Random();
    for (int i = 0; i < 20; i++) {
      Wall wall = new Wall(
          r.nextInt(grid.getCols()),
          r.nextInt(grid.getRows()),
          r.nextInt(2),
          r.ints(5, 10).findFirst().getAsInt()
      );

      boolean intersect = false;
      for (int j = 1; j <= wall.getLength(); j++) {
        Wall wallB;
        if (wall.getDir() == 0) { // 0 = vertical
          wallB = new Wall(wall.startX(), wall.startY() + j, wall.getDir(), 0);
        } else {
          wallB = new Wall(wall.startX() + j, wall.startY(), wall.getDir(), 0);
        }
        if (i != 0) {
          for (int tmp = i; tmp > 1; tmp--) {
            Wall wallc = walls.get(tmp - 1);

            if (wallB.getSpriteS().intersects(wallc.getSpriteS())) {
              wall.changeLength(j - 1);
              intersect = true;
              break;

            } else {
              for (int tmp2 = wallc.getWalls().size(); tmp2 > 0; tmp2--) {
                if (wallB.getSpriteS().intersects(wallc.getWalls().get(tmp2 - 1).getSpriteS())) {
                  wall.changeLength(tmp2 - 1);
                  intersect = true;
                  break;
                }
              }
            }
            if (!intersect) {
              wall.addWall(wallB);
            }
          }
        } else {
          wall.addWall(wallB);
        }
      }
      walls.add(wall);
    }
  }

  public static boolean checkCollisionWithWall(Food food, LinkedList<Wall> walls) { //with walls
    for (int i = 0; i < walls.size(); i++) {
      for (int j = 0; j < walls.get(i).getWalls().size(); j++) {
        if (walls.get(i).getWalls().get(j).getSpriteS().intersects(food.getSprite())) {
          return true;
        }
      }
    }
    return false;
  }

  public static void wallCollusionWithSnake(LinkedList<Wall> walls, Snake snake, long time) {
    if (System.currentTimeMillis() > time + 3000) {
      walls.forEach((wall) -> {
        if (wall.getSpriteS().intersects(snake.getHead())) {
          snake.snakeDied();
        }
        wall.getWalls().forEach(walls1 -> {
          if (walls1.getSpriteS().intersects(snake.getHead())) {
            snake.snakeDied();
          }
        });
      });
    }
  }
}
