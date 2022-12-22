/**
 * It's a JavaFX application that reads a text file and displays the contents of the text file in a
 * table view.
 */
package com.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

import com.example.demo.Dialogs.SaveAccount;
import com.example.demo.Objects.Account;

public class Main extends Application {

    /**
     * Generic start method overriding the application
     * 
     * @param primaryStage Primary Stage to show different FXML scenes
     * @throws Exception Generic exception thrown when running the method
     * @author Mirza Hizriyan-modified
     */
    // private static Scanner input= new Scanner(System.in);

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Setting the primary stage
        primaryStage.setTitle("2048");
        File iconImg = new File("src/main/resources/com/example/demo/logo.png");
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("logo.png")));
        // Read the text file when the game starts
        Account.readFile();
        // Set default CSS
        File css = new File("src/main/resources/com/example/demo/Themes/original.css");
        UserSettings.pathToCSS = getClass().getResource("Themes/original.css");

        File menuFXML = new File("src/main/resources/com/example/demo/Scenes/Menu.fxml");
        Parent menuRoot = FXMLLoader.load(getClass().getResource("Scenes/Menu.fxml"));
        Scene menuScene = new Scene(menuRoot, UserSettings.WIDTH, UserSettings.HEIGHT);
        menuScene.getStylesheets().add(UserSettings.pathToCSS.toExternalForm());

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

    /**
     * Running the application
     * 
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
}
