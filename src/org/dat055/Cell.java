package org.dat055;

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
        color = color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

}