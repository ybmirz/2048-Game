package com.example.demo.Objects;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class Cell {
    private Rectangle rectangle;
    private Pane root;
    private Text textClass;
    private boolean modify = false;

    /**
     * @param modify
     */
    public void setModify(boolean modify) {
        this.modify = modify;
    }

    /**
     * @return boolean
     */
    public boolean getModify() {
        return modify;
    }

    /**
     * Cell constructor; A Cell is a Rectangle object that is shown
     * into a Parent node and edited based on the Game Logic
     * 
     * @param x     X-Position of the cell
     * @param y     Y-Position of the cell
     * @param scale
     * @param root  Parent node to add the cell
     */
    public Cell(double x, double y, double scale, Pane root) {
        rectangle = new Rectangle();
        rectangle.setX(x);
        rectangle.setY(y);
        rectangle.setHeight(scale);
        rectangle.setWidth(scale);
        this.root = root;
        rectangle.setFill(Color.rgb(201, 191, 180, 1));
        this.textClass = TextMaker.getSingleInstance().makeText("0", x, y, root);
        root.getChildren().add(rectangle);
    }

    /**
     * @param textClass
     */
    public void setTextClass(Text textClass) {
        this.textClass = textClass;
    }

    /**
     * Replacing a target cell with the current cell value.
     * 
     * @param cell Target cell
     */
    public void changeCell(Cell cell) {
        TextMaker.changeTwoText(textClass, cell.getTextClass());
        root.getChildren().remove(cell.getTextClass());
        root.getChildren().remove(textClass);

        if (!cell.getTextClass().getText().equals("0")) {
            root.getChildren().add(cell.getTextClass());
        }
        if (!textClass.getText().equals("0")) {
            root.getChildren().add(textClass);
        }
        setColorByNumber(getNumber());
        cell.setColorByNumber(cell.getNumber());
    }

    /**
     * Adding another cell with the current cell object
     * 
     * @param cell Target cell
     */
    public void adder(Cell cell) {
        cell.getTextClass().setText((cell.getNumber() + this.getNumber()) + "");
        textClass.setText("0");
        root.getChildren().remove(textClass);
        cell.setColorByNumber(cell.getNumber());
        setColorByNumber(getNumber());
    }

    /**
     * @param number
     */
    public void setColorByNumber(int number) {
        switch (number) {
            case 0:
                rectangle.setFill(Color.rgb(201, 191, 180, 1));
                break;
            case 2:
                rectangle.setFill(Color.rgb(232, 255, 100, 0.5));
                break;
            case 4:
                rectangle.setFill(Color.rgb(232, 220, 50, 0.5));
                break;
            case 8:
                rectangle.setFill(Color.rgb(232, 200, 44, 0.8));
                break;
            case 16:
                rectangle.setFill(Color.rgb(232, 170, 44, 0.8));
                break;
            case 32:
                rectangle.setFill(Color.rgb(180, 120, 44, 0.7));
                break;
            case 64:
                rectangle.setFill(Color.rgb(180, 100, 44, 0.7));
                break;
            case 128:
                rectangle.setFill(Color.rgb(180, 80, 44, 0.7));
                break;
            case 256:
                rectangle.setFill(Color.rgb(180, 60, 44, 0.8));
                break;
            case 512:
                rectangle.setFill(Color.rgb(180, 30, 44, 0.8));
                break;
            case 1024:
                rectangle.setFill(Color.rgb(250, 0, 44, 0.8));
                break;
            case 2048:
                rectangle.setFill(Color.rgb(250, 0, 0, 1));

        }

    }

    /**
     * @return double
     */
    public double getX() {
        return rectangle.getX();
    }

    /**
     * @return double
     */
    public double getY() {
        return rectangle.getY();
    }

    /**
     * @return int
     */
    public int getNumber() {
        return Integer.parseInt(textClass.getText());
    }

    /**
     * @return Text
     */
    private Text getTextClass() {
        return textClass;
    }

}
