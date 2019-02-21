package org.dat055;

public class TetrominoZ extends ActiveTetromino{
    private String color = "FF0000";
        /**
         * Constructor creates different states for the tetromino
         * @param color of the containing cells.
         */
    public TetrominoZ (){

            // Här skapas state 0 för tetromino Z
            states.add(new State(color,
                    new Coordinate(0,0),
                    new Coordinate(1,0),
                    new Coordinate(1,1),
                    new Coordinate(2,1)));

            // Här skapas state 1 för tetromino Z
            states.add(new State(color,
                    new Coordinate(2,0),
                    new Coordinate(1,1),
                    new Coordinate(2,1),
                    new Coordinate(1,2)));

            // Här skapas state 2 för tetromino Z
            states.add(new State(color,
                    new Coordinate(0,1),
                    new Coordinate(1,1),
                    new Coordinate(1,2),
                    new Coordinate(2,2)));

            // Här skapas state 3 för tetromino Z
            states.add(new State(color,
                    new Coordinate(1,0),
                    new Coordinate(0,1),
                    new Coordinate(1,1),
                    new Coordinate(0,2)));
        }
}
