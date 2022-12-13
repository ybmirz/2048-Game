package com.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;

import java.util.Optional;
import java.util.Scanner;

import com.example.demo.Controller.MenuController;

public class Main extends Application {
    // private static Scanner input= new Scanner(System.in);

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Setting the primary stage
        primaryStage.setTitle("2048");

        // Group menuRoot = new Group();
        Parent menuRoot = FXMLLoader.load(getClass().getResource("./scenes/Menu.fxml"));
        Scene menuScene = new Scene(menuRoot, UserSettings.WIDTH, UserSettings.HEIGHT);

        // Group accountRoot = new Group();
        // Scene accountScene = new Scene(accountRoot, WIDTH, HEIGHT, Color.rgb(150, 20,
        // 100, 0.2));
        // Group getAccountRoot = new Group();
        // Scene getAccountScene = new Scene(getAccountRoot, WIDTH, HEIGHT,
        // Color.rgb(200, 20, 100, 0.2));
        // Group rankRoot = new Group();
        // Scene rankScene = new Scene(rankRoot, WIDTH, HEIGHT, Color.rgb(250, 50, 120,
        // 0.3));
        BackgroundFill background_fill = new BackgroundFill(Color.rgb(120, 100, 100), CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(background_fill);

        Rectangle backgroundOfMenu = new Rectangle(240, 120, Color.rgb(120, 120, 120, 0.2));
        backgroundOfMenu.setX(UserSettings.WIDTH / 2 - 120);
        // menuRoot.getChildren().add(backgroundOfMenu);

        Rectangle backgroundOfMenuForPlay = new Rectangle(240, 140, Color.rgb(120, 20, 100, 0.2));
        backgroundOfMenuForPlay.setX(UserSettings.WIDTH / 2 - 120);
        backgroundOfMenuForPlay.setY(180);
        // accountRoot.getChildren().add(backgroundOfMenuForPlay);

        primaryStage.setScene(menuScene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
