package com.example.demo;

import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;

import java.util.Random;

import com.example.demo.Objects.*;

public class GameScene {
    private static int HEIGHT = 550 ;
    private static int n = 4;
    private final static int distanceBetweenCells = 10;
    private static double LENGTH = (HEIGHT - ((n + 1) * distanceBetweenCells)) / (double) n;

    private TextMaker textMaker = TextMaker.getSingleInstance();
    private Cell[][] cells = new Cell[n][n];

    private Pane root;
    
    private long score = 0;
    private Text scoreText;
    private static boolean isMoved = false;

    public GameScene(Pane gameRootPane, Text scoreText) {
        this.root = gameRootPane;
        this.scoreText = scoreText;
    }

    static void setN(int number) {
        n = number;
        LENGTH = (HEIGHT - ((n + 1) * distanceBetweenCells)) / (double) n;
    }

    public static double getLENGTH() {
        return LENGTH;
    }

    private void randomFillNumber() {
        Cell[][] emptyCells = new Cell[n][n];
        int a = 0;
        int b = 0;
        int aForBound = 0, bForBound = 0;
        outer: for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (cells[i][j].getNumber() == 0) {
                    emptyCells[a][b] = cells[i][j];
                    if (b < n - 1) {
                        bForBound = b;
                        b++;
                    } else {
                        aForBound = a;
                        a++;
                        b = 0;
                        if (a == n)
                            break outer;
                    }
                }
            }
        }

        // Create either 2 or 4 as a random number
        Text text;
        Random random = new Random();
        boolean putTwo = true;
        if (random.nextInt() % 2 == 0)
            putTwo = false;
        int xCell, yCell;
        xCell = random.nextInt(aForBound + 1);
        yCell = random.nextInt(bForBound + 1);
        if (putTwo) {
            text = textMaker.madeText("2", emptyCells[xCell][yCell].getX(), emptyCells[xCell][yCell].getY(), root);
            emptyCells[xCell][yCell].setTextClass(text);
            root.getChildren().add(text);
            emptyCells[xCell][yCell].setColorByNumber(2);
        } else {
            text = textMaker.madeText("4", emptyCells[xCell][yCell].getX(),
                    emptyCells[xCell][yCell].getY(), root);
            emptyCells[xCell][yCell].setTextClass(text);
            root.getChildren().add(text);
            emptyCells[xCell][yCell].setColorByNumber(4);
        }
    }

    private int haveEmptyCell() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (cells[i][j].getNumber() == 0)
                    return 1;
                if (cells[i][j].getNumber() == 2048)
                    return 0;
            }
        }
        return -1;
    }

    private int passDestination(int i, int j, char direct) {
        int coordinate = j;
        switch (direct) {
            case 'l': {
                for (int k = j - 1; k >= 0; k--) {
                    if (cells[i][k].getNumber() != 0) {
                        coordinate = k + 1;
                        break;
                    } else if (k == 0) {
                        coordinate = 0;
                    }
                }
                return coordinate;
            }
            case 'r': {
                coordinate = j;
                for (int k = j + 1; k <= n - 1; k++) {
                    if (cells[i][k].getNumber() != 0) {
                        coordinate = k - 1;
                        break;
                    } else if (k == n - 1) {
                        coordinate = n - 1;
                    }
                }
                return coordinate;
            }
            case 'd': {
                coordinate = i;
                for (int k = i + 1; k <= n - 1; k++) {
                    if (cells[k][j].getNumber() != 0) {
                        coordinate = k - 1;
                        break;

                    } else if (k == n - 1) {
                        coordinate = n - 1;
                    }
                }
                return coordinate;
            }
            case 'u': {
                coordinate = i;
                for (int k = i - 1; k >= 0; k--) {
                    if (cells[k][j].getNumber() != 0) {
                        coordinate = k + 1;
                        break;
                    } else if (k == 0) {
                        coordinate = 0;
                    }
                }
                return coordinate;
            }
        }
        return -1;
    }

    private void moveLeft() {
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < n; j++) {
                moveHorizontally(i, j, passDestination(i, j, 'l'), -1);
            }
        }
    }

    private void moveRight() {
        for (int i = 0; i < n; i++) {
            for (int j = n - 1; j >= 0; j--) {
                moveHorizontally(i, j, passDestination(i, j, 'r'), 1);
            }
        }
    }

    private void moveUp() {
        for (int j = 0; j < n; j++) {
            for (int i = 1; i < n; i++) {
                moveVertically(i, j, passDestination(i, j, 'u'), -1);
            }
        }

    }

    private void moveDown() {
        for (int j = 0; j < n; j++) {
            for (int i = n - 1; i >= 0; i--) {
                moveVertically(i, j, passDestination(i, j, 'd'), 1);
            }
        }

    }

    private void moveHorizontally(int i, int j, int des, int sign) {
        if (isValidDesH(i, j, des, sign)) {
            cells[i][j].adder(cells[i][des + sign]);
            cells[i][des + sign].setModify(true);
            isMoved = true;
        } else if (des != j) {
            cells[i][j].changeCell(cells[i][des]);
            if (cells[i][des].getNumber() != 0)
                isMoved = true;
        }
    }

    private void moveVertically(int i, int j, int des, int sign) {
        if (isValidDesV(i, j, des, sign)) {
            cells[i][j].adder(cells[des + sign][j]);
            cells[des + sign][j].setModify(true);
            isMoved = true;
        } else if (des != i) {
            cells[i][j].changeCell(cells[des][j]);
            if (cells[des][j].getNumber() != 0)
                isMoved = true;
        }
    }

    private boolean isValidDesH(int i, int j, int des, int sign) {
        if (des + sign < n && des + sign >= 0) {
            if (cells[i][des + sign].getNumber() == cells[i][j].getNumber() && !cells[i][des + sign].getModify()
                    && cells[i][des + sign].getNumber() != 0) {
                return true;
            }
        }
        return false;
    }

    private boolean isValidDesV(int i, int j, int des, int sign) {
        if (des + sign < n && des + sign >= 0) {
            if (cells[des + sign][j].getNumber() == cells[i][j].getNumber() && !cells[des + sign][j].getModify()
                    && cells[des + sign][j].getNumber() != 0) {
                return true;
            }
        }
        return false;
    }

    private boolean haveSameNumberNearly(int i, int j) {
        if (i < n - 1 && j < n - 1) {
            if (cells[i + 1][j].getNumber() == cells[i][j].getNumber())
                return true;
            if (cells[i][j + 1].getNumber() == cells[i][j].getNumber())
                return true;
        }
        return false;
    }

    private boolean canNotMove() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (haveSameNumberNearly(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    // Use the modified cell property to determine the sum
    private void sumCellNumbersToScore() {
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++) {
                // Only some when the cell has been modified at this turn
                if (cells[i][j].getModify())
                    score += cells[i][j].getNumber();
                // Remove all extra modified values
                cells[i][j].setModify(false);
            }
    }

    public void game(Scene gameScene, Stage primaryStage, Pane endGroup) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                cells[i][j] = new Cell((j) * LENGTH + (j + 1) * distanceBetweenCells,
                        (i) * LENGTH + (i + 1) * distanceBetweenCells, LENGTH, root);
            }
        }

        // Create two random span
        randomFillNumber();
        randomFillNumber();

        gameScene.addEventHandler(KeyEvent.KEY_PRESSED, key -> {
            Platform.runLater(() -> {
                int haveEmptyCell;
                switch (key.getCode()) {
                    case DOWN:
                        GameScene.this.moveDown();
                        break;
                    case UP:
                        GameScene.this.moveUp();
                        break;
                    case LEFT:
                        GameScene.this.moveLeft();
                        break;
                    case RIGHT:
                        GameScene.this.moveRight();
                        break;
                    default:
                        return;
                }
                // Get sum of modified cells
                GameScene.this.sumCellNumbersToScore();
                // Set score text
                scoreText.setText(String.format("%02d",score));

                // Decide end game
                haveEmptyCell = GameScene.this.haveEmptyCell();
                if (haveEmptyCell == -1) {
                    if (GameScene.this.canNotMove()) {
                        endGroup.setVisible(true);
                        score = 0;
                        scoreText.setText(String.format("%02d",score));
                    }
                } else if (haveEmptyCell == 1 && isMoved) {
                    GameScene.this.randomFillNumber();
                    isMoved = false;
                }
            });
        });
    }
}
