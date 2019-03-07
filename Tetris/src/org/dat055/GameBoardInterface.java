package org.dat055;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author
 * @version 2019-02-26
 */

public interface GameBoardInterface {
    // ta bort radkommentarerna när ni implementerat trellokorten!
    // om en funktion saknas så lägg till

    // trello #6: GameBoard celler
    Cell getCell(Coordinate coord);
    void setCell(Coordinate coord, Cell cell);
    HashMap<Coordinate, Cell> getGameBoard();

    // trello #15: GameBoard - vad som händer när en rad tas bort
    //void clearMultipleLines(ArrayList<Integer> y);

    // trello #14: ActiveTetromino hantering
    Coordinate getTetrominoPosition();
    void setTetrominoPosition(Coordinate coord);
    HashMap<Coordinate,Cell> getTetrominoCells();
    void rotateTetromino();
    void killTetromino();
    void createTetromino();

    // trello #18: Gameboard. undersöker om det finns lines att cleara.
    //ArrayList<Integer> checkLines();
}
