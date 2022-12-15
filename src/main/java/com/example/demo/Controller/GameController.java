package com.example.demo.Controller;

import java.io.IOError;
import java.io.IOException;

import com.example.demo.EndGame;
import com.example.demo.GameScene;
import com.example.demo.UserSettings;
import com.example.demo.Dialogs.SaveAccount;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class GameController extends ReturningController {

    @FXML
    private AnchorPane gameAnchor;

    @FXML
    private Pane gamePane;

    @FXML
    private Pane endPane;
    @FXML
    private Text endScoreText;

    @FXML
    private MenuBar gameMenuBar;

    @FXML
    private Rectangle scoreRec;

    @FXML
    private Text scoreText;
    private static int currentTextLength = 2;
    private static long currentScore = 0;

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
        endPane.setBackground(Background.fill(Color.rgb(255, 255, 255, 0.4)));
        endPane.setVisible(false);
        gamePane.setBackground(Background.fill(Color.rgb(187, 173, 160, 1)));
        new Thread(() -> {
            try {
                Thread.sleep(750);
                Platform.runLater(() -> {
                    Stage primaryStage = (Stage) gameAnchor.getScene().getWindow();

                    GameScene game = new GameScene(gamePane, scoreText);
                    game.game(gameAnchor.getScene(), primaryStage, endPane);
                    gamePane.requestFocus();
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
                setScore(Long.parseLong(scoreText.getText()));
            }

        });

        endPane.visibleProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) {
                backBtn.setDisable(false);
                backBtn.setOpacity(1);
                EndGame.getInstance(endPane).updateGameScore(currentScore, endScoreText);
            }
        });
        ;
    }

    private static void setScore(long score) {
        // Only save the highest current score
        if (score>currentScore)
            currentScore = score;
    }

    @FXML
    void backToMenu(ActionEvent event) {
        Stage primStage = (Stage) backBtn.getScene().getWindow();
        primStage.setScene(getPrevScene());
        primStage.show();
    }

    @FXML
    void retry(ActionEvent event) throws IOException {
        Stage primStage = (Stage) retryBtn.getScene().getWindow();
        FXMLLoader gameLoad = new FXMLLoader(getClass().getResource("../Scenes/GameScene.fxml"));
        Parent gameRoot = gameLoad.load();
        GameController gameController = gameLoad.getController();
        gameController.setPrevScene(retryBtn.getScene());
        primStage.setScene(new Scene(gameRoot, UserSettings.HEIGHT, UserSettings.WIDTH, Color.rgb(189, 177, 92)));
        primStage.show();
    }

    @FXML
    void saveScore(ActionEvent event) throws IOException {
        Stage popupStage = new Stage();
        FXMLLoader saveLoad = new FXMLLoader(SaveAccount.class.getResource("SaveAccount.fxml"));
        Parent saveRoot = saveLoad.load();
        SaveAccount saveController = saveLoad.getController();
        saveController.setPopupStage(popupStage);
        saveController.setScore(currentScore);
        
        Scene saveScene = new Scene(saveRoot);
        popupStage.setScene(saveScene);
        popupStage.setTitle("Save Score");
        popupStage.initModality(Modality.WINDOW_MODAL);
        popupStage.showAndWait();
    }
}
