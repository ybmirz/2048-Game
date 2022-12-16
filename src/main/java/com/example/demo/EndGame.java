package com.example.demo;

import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class EndGame {
    private static EndGame singleInstance = null;
    private Pane EndPane;

    private EndGame(Pane endPane) {
        this.EndPane = endPane;
    }

    /**
     * Creating a new instance of the EndGame
     * 
     * @param endPane Parent node of EndGame
     * @return the Instance of EndGame
     * @author Mirza Hizriyan-modified
     */
    public static EndGame getInstance(Pane endPane) {
        if (singleInstance == null)
            singleInstance = new EndGame(endPane);
        return singleInstance;
    }

    /**
     * Update the game score into the scene node
     * 
     * @param score Score value to update
     * @author Mirza Hizriyan
     */
    public void updateGameScore(long score) {
        VBox endVbox = (VBox) EndPane.getChildren().get(0);
        Text endScoreText = (Text) endVbox.getChildren().get(1);
        endScoreText.setText("Score: " + String.format(" %02d", score));
    }
}
