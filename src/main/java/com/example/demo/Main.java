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

        Parent menuRoot = FXMLLoader.load(getClass().getResource("./scenes/Menu.fxml"));
        Scene menuScene = new Scene(menuRoot, UserSettings.WIDTH, UserSettings.HEIGHT);

        primaryStage.setScene(menuScene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
