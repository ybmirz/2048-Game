package com.example.demo;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.Random;

import com.example.demo.Objects.*;

public class GameScene {
    private static int HEIGHT = 550;
    private static int n = 4;
    private final static int distanceBetweenCells = 10;
    private static double LENGTH = (HEIGHT - ((n + 1) * distanceBetweenCells)) / (double) n;

    private TextMaker textMaker = TextMaker.getSingleInstance();
    private Cell[][] cells = new Cell[n][n];

    private Pane root;

    private long score = 0;
    private Text scoreText;
    private static boolean isMoved = false;

    /**
     * GameScene (logic) constructor; Initialising the parent node
     * used along with the text object used.
     * 
     * @param gameRootPane Parent for the Game Scene
     * @param scoreText    Text to indicate score
     * @author Mirza Hizriyan
     */
    public GameScene(Pane gameRootPane, Text scoreText) {
        this.root = gameRootPane;
        this.scoreText = scoreText;
    }

    /**
     * Set grid size
     * 
     * @param number Grid size
     * @author Mirza Hizriyan-modified
     */
    public void setN(int number) {
        n = number;
        LENGTH = (HEIGHT - ((n + 1) * distanceBetweenCells)) / (double) n;
        cells = new Cell[n][n];
    }

    /**
     * @return double
     */
    public static double getLENGTH() {
        return LENGTH;
    }

    /**
     * GameScene method to randomly fill a cell
     * with a random number.
     */
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

    /**
     * Determine whether there is an empty cell
     * 
     * @return 0 for a Win condition, 1 for there being an empty cell, and -1 for no
     *         empty cell (hence Game Over)
     */
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

    /**
     * Helper method to determine that destination coordinate to a cell
     * 
     * @param i      Initial x-coordinate
     * @param j      Initial y-coordinate
     * @param direct State direction of destination
     * @return Computed coordinate
     */
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

    // #region Moving Methods
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

    /**
     * Helper method to compute a horizontal move on the Cell grid;
     * Computes the destination cell as a valid destination and proceeds
     * to add or replace the destination cell.
     * 
     * @param i    X-coordinate of cell to move
     * @param j    Y-coordinate of cell to move
     * @param des  Supposed destination coordinate to move to
     * @param sign Either left or right move to decide
     * @author Mirza Hizriyan-modified
     */
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

    /**
     * Helper method to compute a horizontal move on the Cell grid;
     * Computes the destination cell as a valid destination and proceeds
     * to add or replace the destination cell.
     * 
     * @param i    X-coordinate of cell to move
     * @param j    Y-coordinate of cell to move
     * @param des  Supposed destination coordinate to move to
     * @param sign Either up or down move to decide
     * @author Mirza Hizriyan-modified
     */
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

    /**
     * Determine whether a cell is a valid destination on the horizontal axis
     * 
     * @param i    X-coordinate of destination cell
     * @param j    Y-coordinate of destination cell
     * @param des  Supposed destination coordinate to move to
     * @param sign Either left or right move to decide
     * @return boolean
     */
    private boolean isValidDesH(int i, int j, int des, int sign) {
        if (des + sign < n && des + sign >= 0) {
            if (cells[i][des + sign].getNumber() == cells[i][j].getNumber() && !cells[i][des + sign].getModify()
                    && cells[i][des + sign].getNumber() != 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * Determine whether a cell is a valid destination on the vertical axis
     * 
     * @param i    X-coordinate of destination cell
     * @param j    Y-coordinate of destination cell
     * @param des  Supposed destination coordinate to move to
     * @param sign Either up or down move to decide
     * @return boolean
     */
    private boolean isValidDesV(int i, int j, int des, int sign) {
        if (des + sign < n && des + sign >= 0) {
            if (cells[des + sign][j].getNumber() == cells[i][j].getNumber() && !cells[des + sign][j].getModify()
                    && cells[des + sign][j].getNumber() != 0) {
                return true;
            }
        }
        return false;
    }

    // #endregion

    /**
     * Finding any possible cells nearby (adjacent) that are of the same number
     * 
     * @param i X-coordinate of cell
     * @param j Y-coordinate of cell
     * @return Boolean value of either found or not
     */
    private boolean haveSameNumberNearly(int i, int j) {
        if (i < n - 1 && j < n - 1) {
            if (cells[i + 1][j].getNumber() == cells[i][j].getNumber())
                return true;
            if (cells[i][j + 1].getNumber() == cells[i][j].getNumber())
                return true;
        }
        return false;
    }

    /**
     * Determining whether the Game can create a next move or not
     * 
     * @return Boolean value stating possibility of next move
     */
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

    /**
     * Game Logic method to retrive the total number of scores
     * on a specific move.
     * A Cell's modified value is used to determine on it's feasibility
     * to be added to the score.
     * 
     * @author Mirza Hizriyan-modified
     */
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

    /**
     * The main game logic method to run, that includes rendering the
     * grid into the Game's Parent node. Also sets the visibility of the EndGame
     * Parent node, to determine a GameOver.
     * 
     * @param gameScene    Game Scene passed from the Controller, for the Game
     * @param primaryStage Current window stage to display onto
     * @param endGroup     Parent node for the End Game scene
     */
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
                scoreText.setText(String.format("%02d", score));

                // Decide end game
                haveEmptyCell = GameScene.this.haveEmptyCell();
                if (haveEmptyCell == -1) {
                    if (GameScene.this.canNotMove()) {
                        endGroup.setVisible(true);
                        score = 0;
                        scoreText.setText(String.format("%02d", score));
                    }
                } else if (haveEmptyCell == 1 && isMoved) {
                    GameScene.this.randomFillNumber();
                    isMoved = false;
                }
            });
        });
    }
}
