package org.dat055;

import java.util.HashMap;

/**
 * A state is a HashMap that represents a Tetromino state - a collection of 4 cells
 *
 * @author Dara Khadjehnouri
 * @version 2019-02-20
 */
public class State {
    private HashMap<Coordinate, Cell> state = new HashMap<Coordinate, Cell>();

    /**
     * @param color  Color of all cells in the state.
     * @param coord1 Coordinate of the 1st cell
     * @param coord2 Coordinate of the 2nd cell
     * @param coord3 Coordinate of the 3rd cell
     * @param coord4 Coordinate of the 4th cell
     */
    public State(String color, Coordinate coord1, Coordinate coord2, Coordinate coord3, Coordinate coord4) {
        this.state.put(coord1, new Cell(color));
        this.state.put(coord2, new Cell(color));
        this.state.put(coord3, new Cell(color));
        this.state.put(coord4, new Cell(color));
    }

    /**
     * Get method for state
     *
     * @return state
     */
    public HashMap<Coordinate, Cell> getHashMap() {
        return this.state;
    }
}
