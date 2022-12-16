package com.example.demo.Objects;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
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

    /**
     * A Comparable method to compare multiple accounts with one another
     * 
     * @param o
     * @return int
     */
    @Override
    public int compareTo(Account o) {
        return Long.compare(o.getScore(), score);
    }

    /**
     * Adding to the current score of an account. Hence,
     * no two accounts can have the same username.
     * 
     * @param score Score to add to the program
     */
    public void addToScore(long score) {
        this.score += score;
    }

    /**
     * Getter of an An account's score
     * 
     * @return long Account object's score
     */
    public long getScore() {
        return score;
    }

    /**
     * Getter of an account's UserName
     * 
     * @return String Account object's username
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Retrieve an account from the Static Arraylist of Accounts
     * Retrievable by username
     * 
     * @param userName Username to search for in the array list
     * @return Account The found account, or null, if not found.
     * @author Mirza Hizriyan-modified
     */
    static Account getAccountbyUsername(String userName) {
        for (Account account : accounts) {
            if (account.getUserName().equals(userName)) {
                return account;
            }
        }
        return null;

    }

    /**
     * Static method to create a new account and directly add them
     * into the ArrayList. Useful to handle specific account creation.
     * 
     * @param userName New account's username
     * @param score    New account's score
     * @return Account Returns the new Account created
     * @author Mirza Hizriyan - modified
     */
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

    /**
     * Static void method to update the file, based on the current
     * accounts in the static Account ArrayList.
     * 
     * @throws IOException File Not Found
     * @author Mirza Hizriyan
     */
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
            if (accountsFileWriter != null) {
                accountsFileWriter.flush();
                accountsFileWriter.close();
            }
        }
    }

    /**
     * Static void method to read the currentfile and parse the text
     * into an Account object, to be put in the static ArrayList
     * 
     * @throws IOException FileNotFound
     * @throws Exception   Error the String List into ArrayList
     * @author Mirza Hizriyan
     */
    public static void readFile() throws IOException, Exception {
        accountsFile = new File(UserSettings.savingFileName + ".txt");
        if (!accountsFile.exists()){
            accountsFileWriter = new FileWriter(UserSettings.savingFileName + ".txt");
        }
        Scanner readScan = new Scanner(accountsFile);
        ArrayList<String> inputList = new ArrayList<String>();
        while (readScan.hasNextLine()) {
            String data = readScan.nextLine();
            inputList.add(data);
        }
        readScan.close();
        accounts = parseListToAccs(inputList);
    }

    /**
     * Static method to parse an input StringList into a usable
     * ArrayList of Accounts
     * 
     * @param input StringList to parse
     * @return ArrayList<Account> Output of ArrayList of Accounts
     * @throws Exception Error parsing an account
     */
    private static ArrayList<Account> parseListToAccs(ArrayList<String> input) throws Exception {
        ArrayList<Account> accList = new ArrayList<Account>();
        for (String s : input) {
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
