package org.dat055;

public class TetrominoS extends ActiveTetromino {

    /**
     * Constructor.
     * @param color The color of the cells in the Tetromino.
     */
    public TetrominoS(String color) {

        // State 1
        this.states.add(new State(color,
                new Coordinate(1, 0),
                new Coordinate(2, 0),
                new Coordinate(0, 1),
                new Coordinate(1, 1))
        );

        // State 2
        this.states.add(new State(color,
                new Coordinate(1, 0),
                new Coordinate(1, 1),
                new Coordinate(2, 1),
                new Coordinate(2, 2))
        );

        // State 3
        this.states.add(new State(color,
                new Coordinate(1, 1),
                new Coordinate(2, 2),
                new Coordinate(0, 0),
                new Coordinate(1, 1))
        );

        // State 4
        this.states.add(new State(color,
                new Coordinate(0, 0),
                new Coordinate(0, 1),
                new Coordinate(1, 1),
                new Coordinate(1, 2))
        );

    }

}
