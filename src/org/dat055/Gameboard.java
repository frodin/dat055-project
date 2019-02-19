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
}