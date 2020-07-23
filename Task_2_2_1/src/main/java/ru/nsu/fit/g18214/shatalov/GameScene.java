package ru.nsu.fit.g18214.shatalov;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GameScene {

  public static Group getGroup() {
    Text snakeTask = new Text("Eat food!");
    snakeTask.setFont(Font.font(null, FontWeight.BOLD, 15));
    snakeTask.setFill(Color.BLACK);
    snakeTask.setX(0);
    snakeTask.setY(20);

    Group gameScene = new Group(snakeTask);
    return gameScene;
  }


}
