package com.example.demo.Controller;

import java.io.IOException;

import javax.swing.Action;

import com.example.demo.GameScene;
import com.example.demo.UserSettings;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MenuController {

    @FXML
    private VBox menuVBox;

    @FXML
    private Text menuTitle;

    @FXML
    private Button settingsBtn;

    @FXML
    private Button startBtn;

    /**
     * onAction Event for the button specified in the FXML
     * Button will start the game by creating the game scene
     * 
     * @param event argument event given by the FXML to Controller class
     */
    @FXML
    void startGame(ActionEvent event) {
        Group gameRoot = new Group();
        Scene gameScene = new Scene(gameRoot, 750, 750, Color.rgb(189, 177, 92));
        GameScene game = new GameScene();
        Group endgameRoot = new  Group();
        Scene endGameScene = new Scene(endgameRoot, 750, 750, Color.rgb(250, 20, 100, 0.2));

        // Gets primary stage from vBox
        Stage primaryStage = (Stage) menuVBox.getScene().getWindow();
        game.game(gameScene, gameRoot, primaryStage, endGameScene, endgameRoot);
        primaryStage.setScene(gameScene);
        primaryStage.show();
    }

    @FXML
    void openSettings(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) menuVBox.getScene().getWindow();
        SettingsController.setPrevScene(menuVBox.getScene());
        Parent settingsRoot = FXMLLoader.load(getClass().getResource("../scenes/SettingsScene.fxml"));
        primaryStage.setScene(new Scene(settingsRoot, UserSettings.HEIGHT, UserSettings.WIDTH));
        primaryStage.show();
    }   

    @FXML
    void quitMenu(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }

}