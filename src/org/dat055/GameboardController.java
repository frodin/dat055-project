package org.dat055;

public class GameboardController {
    private Gameboard gameboard;

    public GameboardController(int width, int height) {
        this.gameboard = new Gameboard(width, height);
    }

    public Coordinate getTetrominoPosition() {
        //return gameboard.getTetrominoPosition();
        return null;
    }
    public void setTetrominoPosition(int x, int y) {
        //gameboard.setTetrominoPosition(new Coordinate(x, y));
    }
    public void tick() {

    }
    public void createRandomTetromino() {
        //gameboard.createTetromino();
    }
    public Gameboard getGameboard() {
        return this.gameboard;
    }
}
