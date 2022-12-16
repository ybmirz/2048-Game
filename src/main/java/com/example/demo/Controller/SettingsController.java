package com.example.demo.Controller;

import com.example.demo.UserSettings;
import com.example.demo.Dialogs.SaveAccount;
import com.example.demo.UserSettings.Difficulty;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SettingsController extends ReturningController {

    @FXML
    private Button applyBtn;

    @FXML
    private Button backBtn;

    @FXML
    private Text diffTitle;

    @FXML
    private Rectangle diffTitleRec;

    @FXML
    private BorderPane difficultyBorderPane;

    @FXML
    private Pane diffImagePane;
    private Difficulty settDiff;

    @FXML
    private Button diffLeftBtn;

    @FXML
    private Button diffRightBtn;

    @FXML
    private VBox settingVBox;

    @FXML
    private Text settingsTextTitle;

    public void initialize() {
        // Set the buttons
        diffLeftBtn.setStyle("-size: 26;" +
                "-fx-min-height: -size;" +
                "-fx-min-width: -size;" +
                "-fx-max-height: -size;" +
                "-fx-max-width: -size;" +
                "-fx-shape: \"M38,8.3v35.4c0,1-1.3,1.7-2.2,0.9L14.6,27.3c-0.8-0.6-0.8-1.9,0-2.5L35.8,7.3C36.7,6.6,38,7.2,38,8.3z\";");
        diffLeftBtn.setPickOnBounds(true);
        diffRightBtn.setStyle("-size: 26;" +
                "-fx-min-height: -size;" +
                "-fx-min-width: -size;" +
                "-fx-max-height: -size;" +
                "-fx-max-width: -size;" +
                "-fx-shape: \"M14,43.7V8.3c0-1,1.3-1.7,2.2-0.9l21.2,17.3c0.8,0.6,0.8,1.9,0,2.5L16.2,44.7C15.3,45.4,14,44.8,14,43.7z\";");
        diffRightBtn.setPickOnBounds(true);

        settDiff = UserSettings.diff;
        new Thread(() -> {
            try {
                Thread.sleep(250);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Platform.runLater(() -> {
                updateButtons();
                updateImage();
                updateText();
            });
        }).start();
    }

    private void updateText() {
        switch (settDiff) {
            case EASY: {
                diffTitle.setText("EASY");
                break;
            }
            case MEDIUM: {
                diffTitle.setText("MEDIUM");
                break;
            }

            case HARD: {
                diffTitle.setText("HARD");
                break;
            }
        }
    }

    private void updateImage() {
        diffImagePane.getChildren().clear();
        Image img = null;
        switch (settDiff) {
            case EASY:
                img = new Image(SaveAccount.class.getResourceAsStream("../Images/2048 3x3.jpg"));
                break;
            case MEDIUM:
                img = new Image(SaveAccount.class.getResourceAsStream("../Images/2048 4x4.png"));
                break;
            case HARD:
                img = new Image(SaveAccount.class.getResourceAsStream("../Images/2048 5x5.jpg"));
                break;
        }
        ImageView iv1 = new ImageView(img);
        iv1.setFitWidth(155);
        iv1.setFitHeight(150);
        diffImagePane.getChildren().addAll(iv1);
    }

    private void updateButtons() {
        switch (settDiff) {
            case EASY:
                diffLeftBtn.setDisable(true);
                diffRightBtn.setDisable(false);
                break;
            case MEDIUM:
                diffLeftBtn.setDisable(false);
                diffRightBtn.setDisable(false);
                break;
            case HARD:
                diffLeftBtn.setDisable(false);
                diffRightBtn.setDisable(true);
                break;
        }
    }

    @FXML
    void returnMenu(ActionEvent event) {
        Stage primStage = (Stage) this.backBtn.getScene().getWindow();
        primStage.setScene(this.getPrevScene());
        primStage.show();
    }

    @FXML
    void applySettings(ActionEvent event) {
        UserSettings.diff = settDiff;

        returnMenu(event);
    }

    // #region Difficulty Settings
    @FXML
    void decreaseDiff(ActionEvent event) {
        switch (settDiff) {
            case EASY:
                System.out.println("Error: Easy Difficulty tried to decrease.");
                break;
            case MEDIUM:
                settDiff = Difficulty.EASY;
                break;
            case HARD:
                settDiff = Difficulty.MEDIUM;
                break;
        }
        updateButtons();
        updateImage();
        updateText();
    }

    @FXML
    void increaseDiff(ActionEvent event) {
        switch (settDiff) {
            case EASY:
                settDiff = Difficulty.MEDIUM;
                break;
            case MEDIUM:
                settDiff = Difficulty.HARD;
                break;
            case HARD:
                System.out.println("Error: Hard Difficulty tried to increase.");
                break;
        }
        updateButtons();
        updateImage();
        updateText();
    }
    // #endregion

    // #region Theme

    // #endregion
}
