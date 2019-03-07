package org.dat055;


import java.util.HashMap;

public interface GameBoardInterface {

    // GameBoard cells
    Cell getCell(Coordinate coord);
    void setCell(Coordinate coord, Cell cell);
    HashMap<Coordinate, Cell> getGameBoard();
    Cell getCell(int xPos, int yPos);
    void setCell(int xPos, int yPos, Cell cell);
    void removeCell(int xPos, int yPos);

    //ActiveTetromino handeling
    Coordinate getTetrominoPosition();
    void setTetrominoPosition(Coordinate coord);
    HashMap<Coordinate,Cell> getTetrominoCells();
    void rotateTetromino();
    void killTetromino();
    void createTetromino();
    void createTetromino(int i);
    HashMap<Coordinate, Cell> getNextTetrominoCells();

    int getHeight();
    int getWidth();
    int getLevel();
    void levelUp();

}
