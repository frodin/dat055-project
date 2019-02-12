package org.dat055;

public class Cell {

    private String color;

    private boolean visible;

    /**
     * Empty constructor
     */
    public Cell() {
        color = "black";
        visible = true;
    }

    /**
     * Constructor
     * @param color The color of the cell.
     * @param visible Whether the cell is visible or not.
     */
    public Cell(String color, boolean visible) {
        color = color;
        visible = visible;
    }

    /**
     * Constructor for only color, sets visible to true.
     * @param color The color of the cell.
     */
    public Cell(String color) {
        color = color;
        visible = true;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}