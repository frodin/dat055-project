package org.dat055;

/**
 * Cell object that the gameboard and tetrominos is made up of.
 * Two kinds of cell, "empty" and "filled"
 *
 * @author
 * @Version 2019-02-20
 */
public class Cell {

    private String color;

    /**
     * Empty constructor
     */
    public Cell() {
        color = "black";
    }


    /**
     * Constructor for choosing color.
     * @param color The color of the cell.
     */
    public Cell(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

}