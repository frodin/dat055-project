package org.dat055.model;

/**
 * Coordinate object which will be associated with
 * each cell on the gameboard and in its internal tetromino
 *
 * @author Max Hansson
 * @version 2019-02-20
 */
public class Coordinate {
    private int xPos;
    private int yPos;

    /**
     * Constructor.
     *
     * @param xPos The initial x position.
     * @param yPos The initial y position.
     */
    public Coordinate(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    /**
     * Gets the X-position integer(xPos) of the current coordinate
     * @return xPos
     */
    public int getXPos() {
        return this.xPos;
    }

    /**
     * Gets the Y-position integer(xPos) of the current coordinate
     * @return yPos
     */
    public int getYPos() {
        return this.yPos;
    }

    /**
     * Checks if the coordinates are equal
     * @param o the coordinate we want to compare
     * @return true if equal
     */
    @Override
    public boolean equals(Object o) {
        Coordinate coord = (Coordinate) o;
        return (this.xPos == coord.getXPos() && this.yPos == coord.getYPos());
    }

    /**
     * Returns the unique hashCode(int) for this object
     * @return int
     */
    @Override
    public int hashCode() {
        return this.xPos * 397 + this.yPos * 397;
    }
}
