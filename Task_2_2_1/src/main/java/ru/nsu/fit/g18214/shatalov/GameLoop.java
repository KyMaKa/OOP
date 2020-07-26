package ru.nsu.fit.g18214.shatalov;

import javafx.application.Application;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.util.Duration;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;

public class GameLoop extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) {

    ArrayList<Food> foods = new ArrayList<>();

    primaryStage.setTitle("Snake The Game");

    Group root = new Group();
    Scene theScene = new Scene(root);
    primaryStage.setScene(theScene);

    Canvas canvas = new Canvas(512, 512);
    root.getChildren().add(canvas);

    GraphicsContext gc = canvas.getGraphicsContext2D();

    Timeline gameLoop = new Timeline();
    gameLoop.setCycleCount(Timeline.INDEFINITE);
    Random r = new Random();
    for (int i = 0; i < 5; i++) {
      foods.add(new Food(512 * r.nextDouble(), 512 * r.nextDouble()));
    }

    final long timeStart = System.currentTimeMillis();

    KeyFrame kf = new KeyFrame(
        Duration.seconds(0.017),
        new EventHandler<ActionEvent>() {
          public void handle(ActionEvent actionEvent) {
            gc.clearRect(0, 0, 512, 512);

            for (int i = 0; i < 5; i++) {
              //Image food = foods.get(i).getImage();
              gc.drawImage(
                  foods.get(i).getImage(), foods.get(i).getX(), foods.get(i).getY());
            }
          }
        });

    gameLoop.getKeyFrames().add(kf);
    primaryStage.show();
    gameLoop.play();

  }

}