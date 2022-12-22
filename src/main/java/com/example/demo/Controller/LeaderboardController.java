package com.example.demo.Controller;

import java.io.File;
import java.io.IOException;

import com.example.demo.UserSettings;
import com.example.demo.Objects.Account;
import com.example.demo.Objects.AccountCell;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.SortType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

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

    /**
     * Initialize LeaderboardController; Method calledback when the FXML Scene
     * loaded. Created to set up the table view, by reading the scores file
     * 
     * @author Mirza Hizriyan
     */
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

    
    /** 
     * Return back to menu callback
     * @param event
     */
    @FXML
    void backMenu(ActionEvent event) {
        Stage primStage = (Stage) this.backBtn.getScene().getWindow();
        primStage.setScene(this.getPrevScene());
        primStage.show();
    }

    
    /** 
     * Reset button to clear static ArrayList of accounts and
     * the leaderboard
     * @param event
     */
    @FXML
    void resetAccounts(ActionEvent event) {
        Account.accounts.clear();
        leaderboard.getItems().clear();
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
