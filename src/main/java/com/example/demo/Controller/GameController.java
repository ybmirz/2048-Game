package com.example.demo.Controller;

import com.example.demo.GameScene;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GameController extends ReturningController {

    @FXML
    private AnchorPane gameAnchor;

    @FXML
    private Group gameGroup;

    @FXML
    private Group endGroup;

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

    @FXML
    private Button backBtn;

    @FXML
    private Button retryBtn;

    @FXML
    private Button saveScore;

    public void initialize() {
        backBtn.setOpacity(0);
        backBtn.setDisable(true);
        endGroup.setVisible(false);
        new Thread(() -> {
            try {
                Thread.sleep(750);
                Platform.runLater(() -> {
                    Stage primaryStage = (Stage) gameAnchor.getScene().getWindow();

                    GameScene game = new GameScene(gameGroup, scoreText);
                    game.game(gameAnchor.getScene(), primaryStage, endGroup);
                    gameGroup.requestFocus();
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

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

        endGroup.visibleProperty().addListener(new ChangeListener<Boolean>() {

            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) {
                backBtn.setDisable(false);
                backBtn.setOpacity(1);

            }

        });
        ;
    }

    @FXML
    void backToMenu(ActionEvent event) {
        Stage primStage = (Stage) this.backBtn.getScene().getWindow();
        primStage.setScene(getPrevScene());
        primStage.show();
    }

    @FXML
    void retry(ActionEvent event) {

    }

    @FXML
    void saveScore(ActionEvent event) {

    }
}
