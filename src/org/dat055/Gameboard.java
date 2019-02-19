package org.dat055;

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

    public Cell getCell(Coordinate coord){
        return gameboard.get(coord);
    }

    public void setCell(Coordinate coord, Cell cell){
        gameboard.put(coord, cell);
    }

    public HashMap<Coordinate, Cell> getGameboard(){
        return gameboard;
    }

    /**
     * implementation for clearing a _single_ line
     * @param y specific row
     */

    @Override
    public void deleteRow(int y) {
        for (int i = 0; i < this.x; i++) {
            setCell(new Coordinate(i, y), null);
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
                Cell myCell = getCell(new Coordinate(j, i));
                if (myCell != null) {
                    setCell(new Coordinate(j, i + 1), myCell);
                    setCell(new Coordinate(j, i), null);
                }
            }
        }
    }
}
