package ru.nsu.fit.g18214.shatalov;

import javafx.animation.Animation;
import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class GameScene extends Application {

  Scene theScene;
  Timeline gameLoop;
  private Grid grid;
  private Canvas escapeMenu;
  private Button playButton;
  private Button exitButton;
  private Group root;
  private Snake snake;
  private GraphicsContext gc;
  private LinkedList<Wall> wallsInBetween = new LinkedList<>();
  private LinkedList<Wall> walls = new LinkedList<>();

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) {
    ArrayList<String> input = new ArrayList<>();
    grid = new Grid(512, 512);
    snake = new Snake(grid.getCols() / 2, grid.getRows() / 2, grid);
    ArrayList<Food> foods = new ArrayList<>();

    primaryStage.setTitle("Snake The Game");

    root = new Group();

    Canvas canvas = new Canvas(512, 512);
    gc = canvas.getGraphicsContext2D();

    root.getChildren().add(canvas);
    theScene = new Scene(root);
    primaryStage.setResizable(false);
    primaryStage.setScene(theScene);
    primaryStage.show();
    escapeMenu = new Canvas(512, 512);

    playButton = new Button("Play");
    playButton.setLayoutX(240);
    playButton.setLayoutY(210);

    exitButton = new Button("Exit");
    exitButton.setLayoutX(240);
    exitButton.setLayoutY(256);

    playButton.setOnMouseClicked((event -> esMenu()));
    exitButton.setOnMouseClicked((event -> {
      MainMenu mainMenu = new MainMenu();
      try {
        mainMenu.start(primaryStage);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }));
    theScene.setOnKeyPressed((keyEvent -> {
      switch (keyEvent.getCode()) {
        case UP:
          snake.dirUp();
          break;
        case DOWN:
          snake.dirDown();
          break;
        case LEFT:
          snake.dirLeft();
          break;
        case RIGHT:
          snake.dirRight();
          break;
        case ESCAPE:
          esMenu();
      }
    }));


    gameLoop = new Timeline();
    gameLoop.setCycleCount(Timeline.INDEFINITE);
    Random r = new Random();
    createWall();
    for (int i = 0; i < 5; i++) {
      Food food = new Food(r.nextInt(grid.getCols()), r.nextInt(grid.getRows()));
      if (checkCollisionWithWall(food)) {
        --i;
      } else {
        foods.add(food);
      }
    }


    KeyFrame kf = new KeyFrame(
        Duration.seconds(0.05),
        actionEvent -> {
          gc.setFill(Grid.COLOR);
          gc.fillRect(0, 0, 512, 512);

          gc.setFill(Wall.COLOR);
          walls.forEach(wall -> wall.getSpriteS().render(gc));
          walls.forEach(wall ->
              wall.getWalls().forEach(swalls ->
                  swalls.getSpriteS().render(gc)));

          gc.setFill(Food.COLOR);
          foods.forEach(food -> food.getSprite().render(gc));

          for (int i = 0; i < 5; i++) {
            if (foods.get(i).getSprite().intersects(snake.getHead())) {
              snake.grow(snake.getHead().positionX + snake.getxVelocity(),
                  snake.getHead().positionY + snake.getyVelocity());
              foods.get(i).getSprite().setPosition(r.nextInt(grid.getCols()), r.nextInt(grid.getRows()));
              while (checkCollisionWithWall(foods.get(i))) {
                foods.get(i).getSprite().setPosition(r.nextInt(grid.getCols()), r.nextInt(grid.getRows()));
              }
            }
          }

          snake.tail.forEach((snakeTail) -> {
            if (snake.getHead().intersects(snakeTail) && snake.getHead() != snakeTail) {
              snakeDead(primaryStage);
            }
          });

          walls.forEach((walls) -> {
            if (walls.getSpriteS().intersects(snake.getHead())) {
              snakeDead(primaryStage);
            }
            walls.getWalls().forEach(walls1 -> {
              if (walls1.getSpriteS().intersects(snake.getHead())) {
                snakeDead(primaryStage);
              }
            });
          });

          if (!snake.isDead()) {
            snake.move();
            gc.setFill(Snake.COLOR);
            snake.tail.forEach(sprite -> sprite.render(gc));
          }
        });

    gameLoop.getKeyFrames().add(kf);
    gameLoop.play();

  }

  private void snakeDead(Stage stage) {
    gameLoop.stop();
    gc.setFill(Snake.COLOR);
    snake.tail.forEach(sprite -> sprite.render(gc));
    gc.setFill(Snake.DEAD);
    snake.getHead().render(gc);
    snake.snakeDied();
    Button restart = new Button("Restart");
    restart.setLayoutX(240);
    restart.setLayoutY(210);

    root.getChildren().add(escapeMenu);
    root.getChildren().add(restart);
    root.getChildren().add(exitButton);

    restart.setOnMouseClicked(event -> {
      GameScene gameScene = new GameScene();
      gameScene.start(stage);
    });

    GraphicsContext gce = escapeMenu.getGraphicsContext2D();
    gce.setFill(Color.GREENYELLOW);
    gce.fillRect(206, 206, 100, 100);
  }

  private void esMenu() {
    if (gameLoop.getStatus() == Animation.Status.RUNNING) {
      gameLoop.stop();
      root.getChildren().add(escapeMenu);
      root.getChildren().add(playButton);
      root.getChildren().add(exitButton);
      GraphicsContext gce = escapeMenu.getGraphicsContext2D();
      gce.setFill(Color.GREENYELLOW);
      gce.fillRect(206, 206, 100, 100);
    } else {
      gameLoop.play();
      root.getChildren().remove(escapeMenu);
      root.getChildren().remove(playButton);
      root.getChildren().remove(exitButton);
    }
  }

  private void createWall() { //in the way that walls don't cross each other
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

  private boolean checkCollisionWithWall(Food food) { //with walls
    for (int i = 0; i < walls.size(); i++) {
      for (int j = 0; j < walls.get(i).getWalls().size(); j++) {
        if (walls.get(i).getWalls().get(j).getSpriteS().intersects(food.getSprite())) {
          return true;
        }
      }
    }
    return false;
  }
}