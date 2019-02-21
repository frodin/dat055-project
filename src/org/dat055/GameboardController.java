package org.dat055;

public class GameboardController {
    private Gameboard gameBoard;

    public Coordinate getTetrominoPosition() {
        return gameBoard.getTetrominoPosition();
    }
    public void setTetrominoPosition(int x, int y) {
        gameBoard.setTetrominoPosition(new Coordinate(x, y));
    }
    public void tick() {

    }
    public void createRandomTetromino() {
        gameBoard.createTetromino();
    }
}
