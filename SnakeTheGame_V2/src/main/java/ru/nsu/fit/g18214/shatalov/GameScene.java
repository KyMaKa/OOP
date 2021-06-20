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

  private final LinkedList<Wall> walls = new LinkedList<>();

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) {
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

    Wall.createWall(grid, walls);

    Food.createFood(grid, foods, walls);

    long time = System.currentTimeMillis();

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

          Food.checkIntersection(snake, foods, grid, walls);

          Snake.headCollusion(snake);


          Wall.wallCollusionWithSnake(walls, snake, time);

          if (!snake.isDead()) {
            snake.move();
            gc.setFill(Snake.COLOR);
            snake.tail.forEach(sprite -> sprite.render(gc));
          } else {
            snakeDead(primaryStage);
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
}