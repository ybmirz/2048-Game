package com.example.demo.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SettingsController extends ReturningController {

    @FXML
    private Button backBtn;

    @FXML
    private VBox settingVBox;

    @FXML
    private Text settingsTextTitle;

    @FXML
    void returnMenu(ActionEvent event) {
        Stage primStage = (Stage) this.backBtn.getScene().getWindow();
        primStage.setScene(this.getPrevScene());
        primStage.show();
    }
}
