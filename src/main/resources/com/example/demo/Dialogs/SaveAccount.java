package com.example.demo.Dialogs;

import com.example.demo.Objects.Account;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
    private long score;

    public <T> void initialize() {
        // Emptry String
        if (usernameText.getText().isEmpty())
            saveScoreBtn.setDisable(true);

        usernameText.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
                if (!usernameText.getText().isEmpty())
                    saveScoreBtn.setDisable(false);
                else
                    saveScoreBtn.setDisable(true);

            }

        });
    }

    @FXML
    void save(ActionEvent event) {
        String newUsername = usernameText.getText();
        newAcc = Account.makeNewAccount(newUsername, score);
        closeStage();
    }

    public void setScore(long Score) {
        this.score = Score;
    }

    public void setPopupStage(Stage stage) {
        this.myStage = stage;
    }

    private void closeStage() {
        if (this.myStage != null)
            this.myStage.close();
    }
}
