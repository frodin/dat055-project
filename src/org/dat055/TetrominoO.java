package org.dat055;

public class TetrominoO extends ActiveTetromino{

    /**
     * Constructor creates different states for the tetromino
     * @param color of the containing cells.
     */
    public TetrominoO (String color){

        // Här skapas state 0 för tetromino O (behövs bara state 0 då Tetromino O är identisk i alla states)

        states.add(new State(color,
                new Coordinate(1,0),
                new Coordinate(2,0),
                new Coordinate(1,1),
                new Coordinate(2,1)));
    }

}
