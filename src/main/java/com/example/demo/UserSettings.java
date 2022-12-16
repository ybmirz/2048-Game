package com.example.demo;

public class UserSettings {
    // Should have difficulty, color, width and height
    public static Difficulty diff = Difficulty.MEDIUM;
    public static int HEIGHT = 700;
    public static int WIDTH = 700;
    public static String savingFileName = "scores";

    public enum Difficulty {
        EASY,
        MEDIUM,
        HARD
    }

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