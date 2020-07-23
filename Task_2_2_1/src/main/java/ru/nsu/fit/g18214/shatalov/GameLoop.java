package ru.nsu.fit.g18214.shatalov;

import javafx.application.Application;
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

public class GameLoop {

  public void gameLoop() {
    Group root = new Group();
    Scene theScene = new Scene(root);

    Canvas canvas = new Canvas(512, 512);
    root.getChildren().add(canvas);

    GraphicsContext gc = new canvas.getGraphicsContext2D();

  }

}
