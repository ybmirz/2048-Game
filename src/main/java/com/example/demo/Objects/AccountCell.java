package com.example.demo.Objects;

import javafx.beans.property.SimpleStringProperty;

public class AccountCell {
    private SimpleStringProperty UserName;
    private SimpleStringProperty score;

    public AccountCell(String username, long score) {
        this.UserName = new SimpleStringProperty(username);
        String scoreInp = String.format("%02d", score);
        this.score = new SimpleStringProperty(scoreInp);
    }

    public String getUserName() {
        return this.UserName.get();
    }

    public long getScore() {
        return Long.parseLong(this.score.get());
    }

    public void setUserName(String username) {
        this.UserName = new SimpleStringProperty(username);
    }

    public void setScore(long score) {
        String scoreInp = String.format("%02d", score);
        this.score = new SimpleStringProperty(scoreInp);
    }

}
