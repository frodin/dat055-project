package org.dat055;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Gameboard implements GameBoardInterface{
    private HashMap<Coordinate, Cell> gameboard;
    private Coordinate gameBoardCoordinate;
    private int width, height;
    private ActiveTetromino activeTetromino;


    public Gameboard(int width, int height){
       this.width = width;
       this.height = height;
       gameboard = new HashMap<>();
       gameBoardCoordinate = new Coordinate(4,0);
    }

    public int getWidth(){
        return this.width;
    }
    public int getHeight(){
        return this.height;
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

    public HashMap<Coordinate, Cell> getGameboard() {
        return gameboard;
    }

    // This method returns all cells in the activeTetromino but with new coordinates related to the gameboard.
    // Now coordinates range from 10x20 and before it was 3x3

    public HashMap<Coordinate,Cell> getAdaptedTetrominoCoordinates(){
        HashMap<Coordinate,Cell> tempHashMap = new HashMap<Coordinate,Cell>();

        //We iterate over all entries in the active tetromino.(No null values)
        for(Map.Entry<Coordinate,Cell> entry : activeTetromino.getState().getHashMap().entrySet()){

            // Assign the tempHashMap with updated coordinates
            tempHashMap.put(new Coordinate(
                    entry.getKey().getXPos()+ gameBoardCoordinate.getXPos(),
                    entry.getKey().getYPos() + gameBoardCoordinate.getYPos()),
                    entry.getValue());
        }

        return tempHashMap;
    }

    /**
     * Rotates the given tetromino
     * @param activeTetromino the tetromino to be rotated
     */
    public void rotateTetromino(ActiveTetromino activeTetromino){
        activeTetromino.rotate();
    }

    /**
     * Merges the given tetromino with the gameboard
     * @param activeTetromino the tetromino to be merged.
     */
    public void killTetromino(ActiveTetromino activeTetromino){

        for(Map.Entry<Coordinate,Cell> entry : activeTetromino.getState().getHashMap().entrySet()){
            setCell(entry.getKey(), entry.getValue());
        }
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
        for (int i = 0; i < this.width; i++) {
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
            for (int j = 0; j < this.width; j++) {
                Cell myCell = getCell(j, i);
                if (myCell != null) {
                    setCell(j, i + 1, myCell);
                    setCell(j, i, null);
                }
            }
        }
    }
}
