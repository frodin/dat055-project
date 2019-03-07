package org.dat055;

/**
 * Creates the diffrent states for the I tetromino
 *
 * @author Philip Hellberg
 * @version 2019-02-21
 */
public class TetrominoI extends ActiveTetromino{
    private String color = "00FFFF";
    /**
     * Constructor creates different states for the tetromino
     */
    public TetrominoI (){

        // Here we create state 0 for tetromino I
        states.add(new State(color,
                new Coordinate(0, 1),
                new Coordinate(1, 1),
                new Coordinate(2, 1),
                new Coordinate(3, 1)));

        // Here we create state 1
        states.add(new State(color,
                new Coordinate(2, 0),
                new Coordinate(2, 1),
                new Coordinate(2, 2),
                new Coordinate(2, 3)));

        // Here we create state 2
        states.add(new State(color,
                new Coordinate(0, 2),
                new Coordinate(1, 2),
                new Coordinate(2, 2),
                new Coordinate(3, 2)));

        // Here we create state 3
        states.add(new State(color,
                new Coordinate(1, 0),
                new Coordinate(1, 1),
                new Coordinate(1, 2),
                new Coordinate(1, 3)));
    }

}
