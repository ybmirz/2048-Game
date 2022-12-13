package com.example.demo.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SettingsController {

    @FXML
    private Button backBtn;

    @FXML
    private VBox settingVBox;

    @FXML
    private Text settingsTextTitle;
    private static Scene prevScene;

    @FXML
    void returnMenu(ActionEvent event) {
        Stage primStage = (Stage) this.backBtn.getScene().getWindow();
        primStage.setScene(prevScene);
        primStage.show();
    }

    public static void setPrevScene(Scene menu) {
        prevScene = menu;
    }
}
