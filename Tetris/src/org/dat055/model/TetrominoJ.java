package org.dat055.model;

/**
 * Creates the diffrent states for tetromino J
 *
 * @author Philip Hellberg
 * @version 2019-02-21
 */
public class TetrominoJ extends ActiveTetromino {
    private String color = "0000FF";

    /**
     * Constructor creates different states for the tetromino
     */
    public TetrominoJ() {

        // State 1
        this.states.add(new State(color,
                new Coordinate(0, 0),
                new Coordinate(0, 1),
                new Coordinate(1, 1),
                new Coordinate(2, 1))
        );

        // State 2
        this.states.add(new State(color,
                new Coordinate(1, 0),
                new Coordinate(2, 0),
                new Coordinate(1, 1),
                new Coordinate(1, 2))
        );

        // State 3
        this.states.add(new State(color,
                new Coordinate(0, 1),
                new Coordinate(1, 1),
                new Coordinate(2, 1),
                new Coordinate(2, 2))
        );

        // State 4
        this.states.add(new State(color,
                new Coordinate(1, 0),
                new Coordinate(1, 1),
                new Coordinate(1, 2),
                new Coordinate(0, 2))
        );
    }

}
