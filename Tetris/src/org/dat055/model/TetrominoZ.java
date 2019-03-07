package org.dat055.model;

/**
 * Creates the diffrent states for tetromino Z
 *
 * @author Philip Hellberg
 * @version 2019-02-21
 */
public class TetrominoZ extends ActiveTetromino {
    private String color = "FF0000";

    /**
     * Constructor creates different states for the tetromino
     */
    public TetrominoZ() {

        // state 0
        states.add(new State(color,
                new Coordinate(0, 0),
                new Coordinate(1, 0),
                new Coordinate(1, 1),
                new Coordinate(2, 1)));

        // state 1
        states.add(new State(color,
                new Coordinate(2, 0),
                new Coordinate(1, 1),
                new Coordinate(2, 1),
                new Coordinate(1, 2)));

        // state 2
        states.add(new State(color,
                new Coordinate(0, 1),
                new Coordinate(1, 1),
                new Coordinate(1, 2),
                new Coordinate(2, 2)));

        // state 3
        states.add(new State(color,
                new Coordinate(1, 0),
                new Coordinate(0, 1),
                new Coordinate(1, 1),
                new Coordinate(0, 2)));
    }
}
