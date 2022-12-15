package com.example.demo.Controller;

import com.example.demo.Objects.Account;
import com.example.demo.Objects.AccountCell;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.SortType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LeaderboardController extends ReturningController {

    @FXML
    private VBox lbVBox;

    @FXML
    private Button backBtn;

    @FXML
    private TableView<AccountCell> leaderboard;

    @FXML
    private Button resetBtn;

    @FXML
    private TableColumn<AccountCell, String> scoreColumn;

    @FXML
    private TableColumn<AccountCell, String> usernameColumn;

    private ObservableList<AccountCell> accountsModel = null;

    public void initialize() {
        leaderboard.setEditable(false);
        leaderboard.setPlaceholder(new Label("No current accounts are saved"));
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("UserName"));
        scoreColumn.setCellValueFactory(new PropertyValueFactory<>("Score"));
        accountsModel = FXCollections.observableArrayList();
        // if accounts not empty
        if (Account.accounts.size() > 0) {
            for (Account a : Account.accounts)
                accountsModel.add(new AccountCell(a.getUserName(), a.getScore()));
        }
        leaderboard.setItems(accountsModel);
        scoreColumn.setSortType(SortType.DESCENDING);
        leaderboard.getSortOrder().setAll(scoreColumn);
    }

    @FXML
    void backMenu(ActionEvent event) {
        Stage primStage = (Stage) this.backBtn.getScene().getWindow();
        primStage.setScene(this.getPrevScene());
        primStage.show();
    }

    @FXML
    void resetAccounts(ActionEvent event) {
        Account.accounts.clear();
        leaderboard.getItems().clear();
    }

}
