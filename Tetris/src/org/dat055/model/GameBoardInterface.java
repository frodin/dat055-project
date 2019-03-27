package org.dat055.model;

import java.util.HashMap;

/**
 * @author Max Hansson
 * @version 2019-02-26
 */


public interface GameBoardInterface {

    Cell getCell(Coordinate coord);

    void setCell(Coordinate coord, Cell cell);

    HashMap<Coordinate, Cell> getGameBoard();


    Coordinate getTetrominoPosition();

    void setTetrominoPosition(Coordinate coord);

    HashMap<Coordinate, Cell> getTetrominoCells();

    void rotateTetromino();

    void killTetromino();

    void createTetromino();

}
