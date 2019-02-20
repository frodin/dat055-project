package org.dat055;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class Gameboard implements GameBoardInterface {
    private HashMap<Coordinate, Cell> gameboard;
    private int y, x;

    public Gameboard(int xLength, int yLength){
        makeGameboard(xLength, yLength);

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

    /**
     * clears any number of lines and lowers above cells
     * @param y array of rows
     */
    @Override
    public void clearMultipleLines(ArrayList<Integer> y) {
        Collections.sort(y);
        y.forEach(i -> clearLine(i));
    }

    /**
     * helper method: clears a line and lowers above cells.
     * @param y specific row
     */
    private void clearLine(int y) {
        deleteRow(y);
        lowerAbove(y);
    }

    /**
     * deletes a row. should always be followed with lowerAbove() call
     * @param y specific row
     */
    private void deleteRow(int y) {
        for (int i = 0; i < this.x; i++) {
            setCell(i, y, null);
        }
    }

    /**
     * lowers all cells on all rows above by 1
     * @param y specific row
     */
    private void lowerAbove(int y) {
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

    /**
     * scans the gameboard for lines to clear
     * @return list of rows to clear
     */
    @Override
    public ArrayList<Integer> checkLines() {
        ArrayList<Integer> lines = new ArrayList<>();
        for (int i = 0; i < this.y; i++) {
            xLoop:
            for (int j = 0; j < this.x; j++) {
                if (getCell(j, i) == null) {
                    break xLoop;
                }
                else if (getCell(j, i) != null && j == this.x - 1) {
                    lines.add(i);
                }
            }
        }
        if (lines.size() > 0) {
            return lines;
        }
        else return null;
    }
}
