package com.example.demo.Controller;

import com.example.demo.GameScene;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GameController {

    @FXML
    private AnchorPane gameAnchor;

    @FXML
    private Group gameGroup;

    @FXML
    private MenuBar gameMenuBar;

    @FXML
    private Rectangle scoreRec;

    @FXML
    private Text scoreText;
    private static int currentTextLength = 2;

    @FXML
    private Text scoreTitle;

    @FXML
    private Rectangle scoreTitleRec;

    public void initialize() {
        new Thread(() -> {
            try {
                Thread.sleep(750);
                Platform.runLater(() -> {
                    Stage primaryStage = (Stage) gameAnchor.getScene().getWindow();
                    Group endgameRoot = new Group();
                    Scene endGameScene = new Scene(endgameRoot, 750, 750, Color.rgb(250, 20, 100, 0.2));

                    GameScene game = new GameScene(gameGroup, scoreText);
                    game.game(gameAnchor.getScene(), primaryStage, endGameScene, endgameRoot);

                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).run();

        // Set a changed listener to the scoreText to edit the rec
        scoreText.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
                String currentText = scoreText.getText();
                if (currentText.length() > currentTextLength) {
                    currentTextLength = currentText.length();
                    // Remove two characters from text
                    currentText = currentText.substring(0, currentText.length() - 2);
                    // Loop through the remaining characters
                    for (char c : scoreText.getText().toCharArray()) {
                        scoreRec.setWidth(scoreRec.getWidth() + 4);
                    }
                }
            }

        });
    }

}
