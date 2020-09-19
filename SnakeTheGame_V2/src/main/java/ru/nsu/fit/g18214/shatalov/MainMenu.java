package ru.nsu.fit.g18214.shatalov;

import java.io.FileNotFoundException;
import java.io.InputStream;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainMenu extends Application {

  @Override
  public void start(Stage primaryStage) throws Exception {

    //BUTTONS
    Button playButton = new Button("Play");
    playButton.setLayoutX(300);
    playButton.setLayoutY(200);

    Button startButton = new Button("Start");
    startButton.setLayoutX(300);
    startButton.setLayoutY(100);

    Button exitButton = new Button("Exit");
    exitButton.setLayoutX(300);
    exitButton.setLayoutY(250);

    //TEXT
    Text menuTitle = new Text("Snake The Game");
    menuTitle.setFont(Font.font(null, FontWeight.BOLD, 20));
    menuTitle.setFill(Color.GREEN);
    menuTitle.setX(210);
    menuTitle.setY(50);

    Text menuMain = new Text("Main menu");
    menuMain.setFont(Font.font(null, FontWeight.BOLD, 15));
    menuMain.setFill(Color.BLACK);
    menuMain.setX(260);
    menuMain.setY(100);

    //GROUPS AND SCENES
    Group root = new Group(startButton);
    Group menu = new Group(menuMain, menuTitle, playButton, exitButton);
    Scene scene = new Scene(root, 600, 300);

    //ACTIONS
    startButton.setOnMouseClicked((new EventHandler<MouseEvent>() {
      public void handle(MouseEvent event) {
        primaryStage.setScene(new Scene(menu, 600, 300));
        primaryStage.show();
      }
    }));

    exitButton.setOnMouseClicked((new EventHandler<MouseEvent>() {
      public void handle(MouseEvent event) {
        primaryStage.close();
      }
    }));

    playButton.setOnMouseClicked((new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {
        //primaryStage.setScene(new Scene(GameScene.getGroup(), 600, 300));
        GameLoop gl = new GameLoop();
        gl.start(primaryStage);
        //primaryStage.show();
      }
    }));

    //WINDOW
    primaryStage.setTitle("SnakeTheGame");
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }

}
