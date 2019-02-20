package org.dat055;

import java.util.Arrays;
import java.util.HashMap;

public class Gameboard implements GameBoardInterface{
    private HashMap<Coordinate, Cell> gameboard;
    private Coordinate activeTetrominoPos;
    private ActiveTetromino activeTetromino;
    private int y, x;

    public Gameboard(int xLength, int yLength){
       // makeGameboard(xLength, yLength);
    }

    public void makeGameboard(int xLength, int yLength){
        for(int i = 0; i < xLength * yLength; i++){
            x = i % yLength;
            y = i / xLength;
            gameboard.put(new Coordinate(x, y), null);
        }
    }

    public Cell getCell(Coordinate coord) {
        return gameboard.get(coord);
    }
    public Cell getCell(int xPos, int yPos){
            return gameboard.get(new Coordinate(xPos, yPos));
    }

    public void setCell(Coordinate coord, Cell cell){
        gameboard.put(coord, cell);
    }
    public void setCell(int xPos, int yPos, Cell cell){
        gameboard.put(new Coordinate(xPos, yPos), cell);
    }

    public HashMap<Coordinate, Cell> getGameboard(){
        return gameboard;
    }

    public Coordinate getTerominoPosition(){
        return activeTetrominoPos;
    }
    public void setTetrominoPosition(Coordinate coord){
        activeTetrominoPos = coord;
    }
    public HashMap<Coordinate,Cell> getTetrominoCells(){
        return activeTetromino.getState().getHashMap();

        // temp = activeTetromino;
    }
    public void rotateTetromino(){
        activeTetromino.rotate();
    }
    public void killTetromino(){

    }
    public void createTetromino(){
        activeTetromino = new ActiveTetromino();

    }

    /**
     * clears any number of lines and lowers above
     * @param y array of rows
     */

    @Override
    public void clearMultipleLines(int[] y) {
        Arrays.sort(y);
        for (int i : y) {
            deleteRow(i);
            lowerAbove(i);
        }
    }

    /**
     * clears a single line. should always be followed with lowerAbove() call
     * @param y specific row
     */

    @Override
    public void deleteRow(int y) {
        for (int i = 0; i < this.x; i++) {
            setCell(i, y, null);
        }
    }

    /**
     * lowers all cells on all rows above by 1
     * @param y specific row
     */

    @Override
    public void lowerAbove(int y) {
        for (int i = y - 1; i >= 0; i--) {
            for (int j = 0; j < this.x; j++) {
                Cell myCell = getCell(j, i);
                if (myCell != null) {
                    setCell(j, i + 1, myCell);
                    setCell(j, i, null);
                }
            }
        }
    }
}
