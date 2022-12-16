package com.example.demo;

import java.net.URL;


public class UserSettings {
    // Should have difficulty, color, width and height
    /**
     * A public static Difficulty to determine the difficulty of the game
     */
    public static Difficulty diff = Difficulty.MEDIUM;
    public static int HEIGHT = 700;
    public static int WIDTH = 700;
    /**
     * A public static fileName to save the static ArrayList of accounts
     */
    public static String savingFileName = "scores";
    /**
     * A static URL for the Path to the CSS File used for Scenes as a theme
     */
    public static URL pathToCSS = null;

    public enum Difficulty {
        EASY,
        MEDIUM,
        HARD
    }

    
    /** 
     * @return int
     */
    public static int getGameDiff() {
        int gridNum = 4;
        switch (UserSettings.diff) {
            case EASY -> gridNum = 3;
            case MEDIUM -> gridNum = 4;
            case HARD -> gridNum = 5;
        }
        return gridNum;
    }
}