package com.example.demo.Objects;

import javafx.beans.property.SimpleStringProperty;

public class AccountCell {
    private SimpleStringProperty UserName;
    private SimpleStringProperty score;

    /**
     * AccountCell is used to parse an Account Object to fit
     * into a TableView, due to its SimpleStringProperty
     * 
     * @param username Account's Username
     * @param score    Account's Score
     * @author Mirza Hizriyan
     */
    public AccountCell(String username, long score) {
        this.UserName = new SimpleStringProperty(username);
        String scoreInp = String.format("%02d", score);
        this.score = new SimpleStringProperty(scoreInp);
    }

    /**
     * Retrieve the user of the account cell
     * Used for the cell in table view parsing
     * 
     * @return String
     * @author Mirza H
     */
    public String getUserName() {
        return this.UserName.get();
    }

    /**
     * Retrieve the score of the account cell
     * Used for the cell in the table view parsing
     * 
     * @return long
     * @author Mirza H
     */
    public long getScore() {
        return Long.parseLong(this.score.get());
    }

    /**
     * Set the username of the account cell
     * 
     * @param username
     * @author Mirza H
     */
    public void setUserName(String username) {
        this.UserName = new SimpleStringProperty(username);
    }

    /**
     * Set the score of the account cell
     * 
     * @param score
     * @author Mirza H
     */
    public void setScore(long score) {
        String scoreInp = String.format("%02d", score);
        this.score = new SimpleStringProperty(scoreInp);
    }

}
