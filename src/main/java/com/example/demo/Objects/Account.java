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
import java.util.List;
import java.util.Scanner;

import com.example.demo.UserSettings;

public class Account implements Comparable<Account> {
    private long score = 0;
    private String userName;
    public static ArrayList<Account> accounts = new ArrayList<Account>();
    private static FileWriter accountsFileWriter = null;
    private static File accountsFile = null;

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
            accountsFileWriter = new FileWriter(UserSettings.savingFileName + ".txt");
            for (Account a : accounts) {
                String newUsername = a.getUserName();
                newUsername.replace(' ', '_');
                accountsFileWriter.write(newUsername + " " + a.getScore() + "\n");
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

    public static void readFile() throws IOException, Exception {
        accountsFile = new File(UserSettings.savingFileName + ".txt");
        Scanner readScan = new Scanner(accountsFile);
        ArrayList<String> inputList = new ArrayList<String>();
        while (readScan.hasNextLine()) {
            String data = readScan.nextLine();
            inputList.add(data);
        }
        readScan.close();
        accounts = parseListToAccs(inputList);
    }

    private static ArrayList<Account> parseListToAccs(ArrayList<String> input) throws Exception {
        ArrayList<Account> accList = new ArrayList<Account>();
        for (String s: input) {
            String[] values = s.split(" ");
            if (values.length > 2) 
                throw new Exception("Error parsing the text file. More than 2 words exist");
            values[0].replace('_', ' ');
            Account a = new Account(values[0]);
            a.addToScore(Long.parseLong(values[1]));
            accList.add(a);
        }
        return accList;
    }

}
