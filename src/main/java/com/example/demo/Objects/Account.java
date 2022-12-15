package com.example.demo.Objects;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Account implements Comparable<Account> {
    private long score = 0;
    private String userName;
    public static ArrayList<Account> accounts = new ArrayList<Account>();
    private static FileWriter accountsFileWriter = null;

    public Account(String userName) {
        this.userName = userName;
    }

    @Override
    public int compareTo(Account o) {
        return Long.compare(o.getScore(), score);
    }

    public void addToScore(long score) {
        this.score += score;
    }

    public long getScore() {
        return score;
    }

    public String getUserName() {
        return userName;
    }

    static Account getAccountbyUsername(String userName) {
        for (Account account : accounts) {
            if (account.getUserName().equals(userName)) {
                return account;
            }
        }
        return null;

    }

    public static Account makeNewAccount(String userName, long score) {
        // Return an existing account if exists
        Account check = getAccountbyUsername(userName);
        if (check != null) {
            check.addToScore(score);
            return check;
        }
        // else create new account if does not exist
        Account account = new Account(userName);
        account.addToScore(score);
        // Need to update to the leaderboards as well
        accounts.add(account);
        return account;
    }

    public static void updateFile() throws IOException {
        try {
            accountsFileWriter = new FileWriter("scores.txt");
            for (Account a : accounts) {
                String newUsername = a.getUserName();
                newUsername.replace(' ', '_');
                accountsFileWriter.write(newUsername + " " + a.getScore());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (accountsFileWriter != null){
                accountsFileWriter.flush();
                accountsFileWriter.close();
            }
        }
    }

}
