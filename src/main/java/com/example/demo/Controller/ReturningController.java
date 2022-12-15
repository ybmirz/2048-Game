package com.example.demo.Controller;

import javafx.scene.Scene;

public class ReturningController {
    private Scene prevScene;
    public void setPrevScene(Scene prev) {
        this.prevScene = prev;
    }
    public Scene getPrevScene() {
        return this.prevScene;
    }
}
