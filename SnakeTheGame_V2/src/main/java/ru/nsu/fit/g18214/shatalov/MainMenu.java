package ru.nsu.fit.g18214.shatalov;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainMenu extends Application {

  @Override
  public void start(Stage primaryStage) throws Exception {

    //BUTTONS
    Button playButton = new Button("Play");
    playButton.setLayoutX(240);
    playButton.setLayoutY(256);

    Button startButton = new Button("Start");
    startButton.setLayoutX(240);
    startButton.setLayoutY(210);

    Button exitButton = new Button("Exit");
    exitButton.setLayoutX(240);
    exitButton.setLayoutY(300);

    //TEXT
    Text menuTitle = new Text("Snake The Game");
    menuTitle.setFont(Font.font(null, FontWeight.BOLD, 20));
    menuTitle.setFill(Color.GREEN);
    menuTitle.setX(190);
    menuTitle.setY(20);

    Text menuMain = new Text("Main menu");
    menuMain.setFont(Font.font(null, FontWeight.BOLD, 15));
    menuMain.setFill(Color.BLACK);
    menuMain.setX(220);
    menuMain.setY(50);

    //GROUPS AND SCENES
    Group root = new Group(startButton);
    Group menu = new Group(menuMain, menuTitle, playButton, exitButton);
    Scene scene = new Scene(root, 512, 512);

    //ACTIONS
    startButton.setOnMouseClicked((event -> {
      primaryStage.setScene(new Scene(menu, 512, 512));
      primaryStage.show();

    }));

    exitButton.setOnMouseClicked((event -> primaryStage.close()));

    playButton.setOnMouseClicked((event -> {
      GameScene gl = new GameScene();
      gl.start(primaryStage);

    }));

    //WINDOW
    primaryStage.setResizable(false);
    primaryStage.setTitle("SnakeTheGame");
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }

}
