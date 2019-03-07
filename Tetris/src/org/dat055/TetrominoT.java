package org.dat055;

/**
 * Creates the diffrent states for tetromino T
 *
 * @author Philip Hellberg
 * @Version 2019-02-21
 */
public class TetrominoT extends ActiveTetromino {
    private String color = "800080";

    /**
     * Constructor creates different states for the tetromino
     *
     * @param color of the containing cells.
     */
    public TetrominoT() {

        // state 0
        states.add(new State(color,
                new Coordinate(1, 0),
                new Coordinate(0, 1),
                new Coordinate(1, 1),
                new Coordinate(2, 1)));

        // state 1
        states.add(new State(color,
                new Coordinate(1, 0),
                new Coordinate(1, 1),
                new Coordinate(2, 1),
                new Coordinate(1, 2)));

        // state 2
        states.add(new State(color,
                new Coordinate(0, 1),
                new Coordinate(1, 1),
                new Coordinate(2, 1),
                new Coordinate(1, 2)));

        // state 3
        states.add(new State(color,
                new Coordinate(1, 0),
                new Coordinate(1, 1),
                new Coordinate(1, 2),
                new Coordinate(0, 1)));
    }

}
