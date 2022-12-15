package com.example.demo.Dialogs;

import com.example.demo.Objects.Account;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SaveAccount {

    @FXML
    private Button saveScoreBtn;
    private Account newAcc;

    @FXML
    private TextField usernameText;
    private Stage myStage;

    @FXML
    void save(ActionEvent event) {
        
        closeStage();
    }

    public void setPopupStage(Stage stage){
        this.myStage = stage;
    }

    private void closeStage() {
        if (this.myStage != null)
            this.myStage.close();
    }
}
