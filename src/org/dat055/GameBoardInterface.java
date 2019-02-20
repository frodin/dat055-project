package org.dat055;

import java.util.HashMap;

public interface GameBoardInterface {
    // ta bort radkommentarerna när ni implementerat trellokorten!
    // om en funktion saknas så lägg till

    // trello #6: GameBoard celler
    Cell getCell(Coordinate coord);
    void setCell(Coordinate coord, Cell cell);
    HashMap<Coordinate, Cell> getGameBoard();

    // trello #15: GameBoard - vad som händer när en rad tas bort
    void deleteRow(int y);
    void lowerAbove(int y);

    // trello #14: ActiveTetromino hantering
    Coordinate getTetrominoPosition();
    void setTetrominoPosition(Coordinate coord);
    HashMap<Coordinate,Cell> getTetrominoCells();
    void rotateTetromino();
    void killTetromino();
    void createTetromino();
}
