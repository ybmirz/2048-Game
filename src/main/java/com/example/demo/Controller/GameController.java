package com.example.demo.Controller;

import java.io.File;
import java.io.IOException;

import com.example.demo.EndGame;
import com.example.demo.GameScene;
import com.example.demo.UserSettings;
import com.example.demo.Dialogs.SaveAccount;
import com.example.demo.Objects.Account;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
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

    /**
     * Initialize GameController; Method calledback when the FXML Scene
     * loaded. Created to set up the scene as necessary based on conditions,
     * and additionally adding in Event Handlers.
     * 
     * @author Mirza Hizriyan
     */
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
                    game.setN(UserSettings.getGameDiff());
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
                EndGame.getInstance(endPane).updateGameScore(currentScore);
                endPane.getScene().removeEventHandler(KeyEvent.KEY_PRESSED, key -> {
                });
            }
        });
        ;
    }

    /**
     * Set the current score saved in the Controller, based off
     * the GameScene logic
     * 
     * @param score Score passed to be assigned
     * @author Mirza Hizriyan
     */
    private static void setScore(long score) {
        // Only save the highest current score
        if (score > currentScore)
            currentScore = score;
    }

    /**
     * ActionMethod for the Back to Menu button
     * 
     * @param event
     */
    @FXML
    void backToMenu(ActionEvent event) {
        Stage primStage = (Stage) backBtn.getScene().getWindow();
        primStage.setScene(getPrevScene());
        primStage.show();
    }

    /**
     * Callback method for Retry Button press after a Game Over.
     * 
     * @param event
     * @throws IOException
     * @author Mirza Hizriyan
     */
    @FXML
    void retry(ActionEvent event) throws IOException {
        Stage primStage = (Stage) retryBtn.getScene().getWindow();
        File gameScene = new File("src/main/resources/com/example/demo/Scenes/GameScene.fxml");
        FXMLLoader gameLoad = new FXMLLoader(gameScene.toURI().toURL());
        Parent gameRoot = gameLoad.load();
        GameController gameController = gameLoad.getController();
        gameController.setPrevScene(this.getPrevScene());
        primStage.setScene(new Scene(gameRoot, UserSettings.HEIGHT, UserSettings.WIDTH, Color.rgb(189, 177, 92)));
        primStage.show();
    }

    /**
     * Callback method for Save Score button press after a Game over,
     * to save the score into the static Accounts array list.
     * 
     * @param event
     * @throws IOException
     * @author Mirza Hizriyan
     */
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


    //#region menuBar
    @FXML
    private MenuBar menuBar;

    @FXML
    private MenuItem chooseSaveFile;

    @FXML
    private MenuItem closeWindow;

    @FXML
    private MenuItem howToPlay;

    @FXML
    private MenuItem about;

    @FXML
    void howToPlay(ActionEvent event) {
        // Open browser on how to play
    }

    @FXML
    void chooseSaveFile(ActionEvent event) {
        Stage prim = (Stage) this.menuBar.getScene().getWindow();
        FileChooser fc = new FileChooser();
        fc.setTitle("Choose Saving File");
        fc.getExtensionFilters().add(new ExtensionFilter("Empty Text File", "*.txt"));
        File file = fc.showOpenDialog(prim);

        if (file != null)
            UserSettings.savingFileName = file.getPath()
                    .substring(file.getPath().lastIndexOf('\\') + 1);
    }

    @FXML
    void closeWindow(ActionEvent event) throws IOException {
        Account.updateFile();
        Platform.exit();
        System.exit(0);
    }

    @FXML
    void about(ActionEvent event) {

    }
    //#endregion
}
