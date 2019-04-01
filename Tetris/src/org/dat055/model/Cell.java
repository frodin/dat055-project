package org.dat055.model;

/**
 * Cell object that the gameboard and tetrominos is made up of.
 * Two kinds of cell, "empty" and "filled"
 *
 * @author Max Hansson
 * @version 2019-02-20
 */
public class Cell {

    private String color;

    /**
     * Empty constructor, initiates the cells color to black
     */
    public Cell() {
        color = "black";
    }


    /**
     * Constructor for choosing color.
     *
     * @param color The color of the cell.
     */
    public Cell(String color) {
        this.color = color;
    }

    /**
     * Gets the String value of the cell
     * @return color value
     */
    public String getColor() {
        return color;
    }

    /**
     * Sets the color of the cell to the specified String parameter
     * @param color the color
     */
    public void setColor(String color) {
        this.color = color;
    }

}