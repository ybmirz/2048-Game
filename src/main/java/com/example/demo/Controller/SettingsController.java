package com.example.demo.Controller;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import com.example.demo.UserSettings;
import com.example.demo.Dialogs.SaveAccount;
import com.example.demo.Objects.Account;
import com.example.demo.UserSettings.Difficulty;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

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

    @FXML
    private Button themeCombo;
    private File fileChosen = null;

    @FXML
    private Text selectedTitle;

    /**
     * Initialize LeaderboardController; Method calledback when the FXML Scene
     * loaded. Created to set up the layout of the settings page, and setting up the
     * necesary details of the page
     * 
     * @author Mirza Hizriyan
     */
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
                try {
                    updateImage();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                updateText();

                if (UserSettings.pathToCSS != null)
                    selectedTitle.setText("Selected: " + UserSettings.pathToCSS.getPath()
                            .substring(UserSettings.pathToCSS.getPath().lastIndexOf('/') + 1));
            });
        }).start();
    }

    /**
     * @param event
     */
    @FXML
    void returnMenu(ActionEvent event) {
        Stage primStage = (Stage) this.backBtn.getScene().getWindow();
        primStage.setScene(this.getPrevScene());
        primStage.show();
    }

    /**
     * Sets all the necessary user settings, such as difficulty and themepath
     * Callback when Apply button is pressed
     * 
     * @param event
     * @author Mirza Hizriyan
     * @throws MalformedURLException
     */
    @FXML
    void applySettings(ActionEvent event) throws MalformedURLException {
        UserSettings.diff = settDiff;
        if (fileChosen != null)
            UserSettings.pathToCSS = fileChosen.toURI().toURL();
    }

    // #region Difficulty Settings
    /**
     * Button callback to decrease the difficulty
     * Simply renders the new chosen option, only applies when the apply button is
     * pressed
     * 
     * @param event
     * @author Mirza Hizriyan
     * @throws Exception
     */
    @FXML
    void decreaseDiff(ActionEvent event) throws Exception {
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

    /**
     * Button callback to increase the difficulty
     * Simply renders the new chosen option, only applies when the apply button is
     * pressed
     * 
     * @param event
     * @author Mirza Hizriyan
     * @throws Exception
     */
    @FXML
    void increaseDiff(ActionEvent event) throws Exception {
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

    /**
     * Updates the rendered Text based on current Difficulty
     * 
     * @author Mirza Hizriyan
     */
    private void updateText() {
        switch (settDiff) {
            case EASY: {
                diffTitle.setText("3x3");
                break;
            }
            case MEDIUM: {
                diffTitle.setText("4x4");
                break;
            }

            case HARD: {
                diffTitle.setText("5x5");
                break;
            }
        }
    }

    /**
     * Updates the rendered Image based on current Difficulty
     * @throws Exception
     */
    private void updateImage() throws Exception {
        diffImagePane.getChildren().clear();
        File img = null;
        switch (settDiff) {
            case EASY:
                img = new File("src/main/resources/com/example/demo/Images/2048 3x3.jpg");
                break;
            case MEDIUM:
                img = new File("src/main/resources/com/example/demo/Images/2048 4x4.png");
                break;
            case HARD:
                img = new File("src/main/resources/com/example/demo/Images/2048 5x5.jpg");
                break;
        }
        if (img == null)
            throw new Exception("Error: Image is NULL or Not Found");
        ImageView iv1 = new ImageView(new Image(img.toURI().toString()));
        iv1.setFitWidth(155);
        iv1.setFitHeight(150);
        diffImagePane.getChildren().addAll(iv1);
    }

    /**
     * Updates the rendered buttons' enabled based on current Difficulty
     */
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
    // #endregion

    // #region Theme
    /**
     * Change theme method callback when button is pressed to choose a theme file
     * Sets the static filepath URL to the CSS file
     * 
     * @param event
     * @author Mirza Hizriyan
     */
    @FXML
    void changeTheme(ActionEvent event) throws MalformedURLException {
        Stage prim = (Stage) this.themeCombo.getScene().getWindow();
        FileChooser fc = new FileChooser();
        fc.setTitle("Choose Theme File");
        fc.getExtensionFilters().add(new ExtensionFilter("Cascading Style Sheets (*.css)", "*.css"));
        fileChosen = fc.showOpenDialog(prim);

        // Change the text
        if (fileChosen != null)
            selectedTitle.setText("Selected: " + fileChosen.getPath()
                    .substring(fileChosen.getPath().lastIndexOf('/') + 1));
    }

    // #endregion

    // #region menuBar
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
    // #endregion
}
