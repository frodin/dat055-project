package org.dat055;

public class Coordinate {
    private int xPos;
    private int yPos;

    /**
     * Constructor.
     * @param xPos The initial x position.
     * @param yPos The initial y position.
     */
    public Coordinate(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public int getXPos() {
        return this.xPos;
    }

    public int getYPos() {
        return this.yPos;
    }
}
