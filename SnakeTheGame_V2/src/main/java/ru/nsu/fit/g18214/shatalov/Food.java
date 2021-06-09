package ru.nsu.fit.g18214.shatalov;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class Food {
  int x;
  int y;
  Sprite food;
  public static final Color COLOR = Color.ROSYBROWN;

  public Food(int x, int y) {
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

  public static void createFood(Grid grid, ArrayList<Food> foods, LinkedList<Wall> walls) {
    Random r = new Random();
    for (int i = 0; i < 5; i++) {
      Food food = new Food(r.nextInt(grid.getCols()), r.nextInt(grid.getRows()));
      if (Wall.checkCollisionWithWall(food, walls)) {
        --i;
      } else {
        foods.add(food);
      }
    }
  }

  public static void checkIntersection(Snake snake, ArrayList<Food> foods, Grid grid, LinkedList<Wall> walls) {
    Random r = new Random();
    for (int i = 0; i < 5; i++) {
      if (foods.get(i).getSprite().intersects(snake.getHead())) {
        snake.grow(snake.getHead().positionX + snake.getxVelocity(),
            snake.getHead().positionY + snake.getyVelocity());
        foods.get(i).getSprite().setPosition(r.nextInt(grid.getCols()), r.nextInt(grid.getRows()));
        while (Wall.checkCollisionWithWall(foods.get(i), walls)) {
          foods.get(i).getSprite().setPosition(r.nextInt(grid.getCols()), r.nextInt(grid.getRows()));
        }
      }
    }
  }
}