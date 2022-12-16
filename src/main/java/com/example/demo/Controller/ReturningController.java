package com.example.demo.Controller;

import javafx.scene.Scene;

public class ReturningController {
    /**
     * Parent class to set up a returning scene,
     * so a "back button" can be functional
     * 
     * @author Mirza Hizriyan
     */
    private Scene prevScene;

    /**
     * Set the previous scene to this controller
     * 
     * @param prev
     * @author Mirza H
     */
    public void setPrevScene(Scene prev) {
        this.prevScene = prev;
    }

    /**
     * Getter for the previous scene of this controller
     * 
     * @return Scene Previous Scene
     * @author Mirza H
     */
    public Scene getPrevScene() {
        return this.prevScene;
    }
}
