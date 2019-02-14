package org.dat055;


public class TetrominoI extends ActiveTetromino{
    /**
     * Constructor creates different stats for the tetromino
     * @param color
     */
    public TetrominoI (String color){

        // Här skapas state 0 för tetromino I
        states.add(new State(color,
                new Coordinate(0, 1),
                new Coordinate(1, 1),
                new Coordinate(2, 1),
                new Coordinate(3, 1)));

        // Här skapas state 1
        states.add(new State(color,
                new Coordinate(2, 0),
                new Coordinate(2, 1),
                new Coordinate(2, 2),
                new Coordinate(2, 3)));

        // Här skapas state 2
        states.add(new State(color,
                new Coordinate(0, 2),
                new Coordinate(1, 2),
                new Coordinate(2, 2),
                new Coordinate(3, 2)));

        // Här skapas state 3
        states.add(new State(color,
                new Coordinate(1, 0),
                new Coordinate(1, 1),
                new Coordinate(1, 2),
                new Coordinate(1, 3)));
    }

}
