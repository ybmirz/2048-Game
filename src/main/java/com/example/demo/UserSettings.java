package com.example.demo;


public class UserSettings {
    // Should have difficulty, color, width and height
    public static Difficulty diff;
    public static int HEIGHT = 700;
    public static int WIDTH = 700;

    enum Difficulty {
        EASY,
        MEDIUM,
        HARD
    }
}