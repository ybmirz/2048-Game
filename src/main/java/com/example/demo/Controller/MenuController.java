package com.example.demo.Controller;

import java.io.File;
import java.io.IOException;

import com.example.demo.Main;
import com.example.demo.UserSettings;
import com.example.demo.Objects.Account;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public class MenuController {

    @FXML
    private VBox menuVBox;

    @FXML
    private Text menuTitle;

    @FXML
    private Button settingsBtn;

    @FXML
    private Button startBtn;

    @FXML
    private Button leaderboardBtn;

    /**
     * onAction Event for the button specified in the FXML
     * Button will start the game by creating the game scene
     * 
     * @param event argument event given by the FXML to Controller class
     * @author Mirza Hizriyan
     */
    @FXML
    void startGame(ActionEvent event) throws IOException {
        // Gets primary stage from vBox
        Stage primaryStage = (Stage) menuVBox.getScene().getWindow();
        File gameScene = new File("src/main/resources/com/example/demo/Scenes/GameScene.fxml");
        FXMLLoader gameLoad = new FXMLLoader(Main.class.getResource("Scenes/GameScene.fxml"));
        Parent gameRoot = gameLoad.load();
        GameController gameController = gameLoad.getController();
        gameController.setPrevScene(menuVBox.getScene());
        primaryStage.setScene(new Scene(gameRoot, UserSettings.HEIGHT, UserSettings.WIDTH, Color.rgb(189, 177, 92)));
        primaryStage.show();
    }

    /**
     * onAction Event for the button specified in the FXML
     * Button will open the leaderboard by creating the leaderboard scene
     * 
     * @param event argument event given by the FXML to Controller class
     * @author Mirza Hizriyan
     */
    @FXML
    void openLeaderboard(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) menuVBox.getScene().getWindow();
        File lbScene = new File("src/main/resources/com/example/demo/Scenes/Leaderboard.fxml");
        FXMLLoader lbLoad = new FXMLLoader(Main.class.getResource("Scenes/Leaderboard.fxml"));
        Parent lbRoot = lbLoad.load();
        LeaderboardController lbControl = lbLoad.getController();
        lbControl.setPrevScene(menuVBox.getScene());
        primaryStage.setScene(new Scene(lbRoot, UserSettings.HEIGHT, UserSettings.WIDTH));
        primaryStage.show();
    }

    /**
     * onAction Event for the button specified in the FXML
     * Button will open the settings page by creating the settings scene
     * 
     * @param event argument event given by the FXML to Controller class
     * @author Mirza Hizriyan
     */
    @FXML
    void openSettings(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) menuVBox.getScene().getWindow();
        File settScene = new File("src/main/resources/com/example/demo/Scenes/SettingsScene.fxml");
        FXMLLoader settingsLoad = new FXMLLoader(Main.class.getResource("Scenes/SettingsScene.fxml"));
        Parent settingsRoot = settingsLoad.load();
        SettingsController settingsController = settingsLoad.getController();
        settingsController.setPrevScene(menuVBox.getScene());
        primaryStage.setScene(new Scene(settingsRoot, UserSettings.HEIGHT, UserSettings.WIDTH));
        primaryStage.show();
    }

    /**
     * Quitting the game through the menu, and updating the scorelist
     * before the application ends.
     * 
     * @param event
     * @throws IOException
     * @author Mirza Hizriyan
     */
    @FXML
    void quitMenu(ActionEvent event) throws IOException {
        // Update the scorelist first
        Account.updateFile();
        Platform.exit();
        System.exit(0);
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