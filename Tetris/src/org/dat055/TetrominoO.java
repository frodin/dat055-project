package org.dat055;

/**
 * Creates the diffrent states for teromino O
 *
 * @author
 * @version
 */
public class TetrominoO extends ActiveTetromino{
    private String color = "FFFF00";
    /**
     * Constructor creates different states for the tetromino
     * @param color of the containing cells.
     */
    public TetrominoO (){

        // state 0 för tetromino O (Only needs one state because all states are identical)
        states.add(new State(color,
                new Coordinate(1,0),
                new Coordinate(2,0),
                new Coordinate(1,1),
                new Coordinate(2,1)));
    }

}
