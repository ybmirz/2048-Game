package com.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;

import com.example.demo.Dialogs.SaveAccount;
import com.example.demo.Objects.Account;

public class Main extends Application {
    // private static Scanner input= new Scanner(System.in);

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Setting the primary stage
        primaryStage.setTitle("2048");
        primaryStage.getIcons().add(new Image(SaveAccount.class.getResourceAsStream("../logo.png")));
        // Read the text file when the game starts
        Account.readFile();
        
        Parent menuRoot = FXMLLoader.load(getClass().getResource("./scenes/Menu.fxml"));
        Scene menuScene = new Scene(menuRoot, UserSettings.WIDTH, UserSettings.HEIGHT);

        primaryStage.setScene(menuScene);
        primaryStage.show();
        // Ensure to update the file when closing
        primaryStage.setOnCloseRequest(event -> {
            try {
                Account.updateFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
