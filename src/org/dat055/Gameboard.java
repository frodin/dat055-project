package org.dat055;

import java.util.*;

public class Gameboard {
    private HashMap<Coordinate, Cell> gameboard;
    private int width, height;

    public Gameboard(int width, int height){
       this.width = width;
       this.height = height;
       gameboard = new HashMap<>();

    }

    /*public void makeGameboard(int xLength, int yLength){
        for(int i = 0; i < xLength * yLength; i++){
            x = i % yLength;
            y = i / xLength;
            gameboard.put(new Coordinate(x, y), null);
        }
    }
    */

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

    public void removeCell(Coordinate coord) {
        gameboard.remove(coord);
    }

    public void removeCell(int xPos, int yPos) {
        gameboard.remove(new Coordinate(xPos, yPos));
    }

    public HashMap<Coordinate, Cell> getGameboard(){
        return gameboard;
    }

    /**
     * clears any number of lines and lowers above cells
     * @param y array of rows
     */
    //@Override
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

    public void deleteRow(int y) {
        for (int i = 0; i < this.width; i++) {
            removeCell(i, y);
        }
    }

    /**
     * lowers all cells on all rows above by 1
     * @param y specific row
     */
    private void lowerAbove(int y) {
        for (int i = y - 1; i >= 0; i--) {
            for (int j = 0; j < this.width; j++) {
                Cell myCell = getCell(j, i);
                if (myCell != null) {
                    setCell(j, i + 1, myCell);
                    removeCell(j, i);
                }
            }
        }
    }

    /**
     * scans the gameboard for lines to clear
     * @return list of rows to clear, will be empty if no lines found.
     */
    //@Override
    public ArrayList<Integer> checkLines() {
        ArrayList<Integer> lines = new ArrayList<>();

        int[] temp = new int[this.height];
        for (int i = 0; i < temp.length; i++) {
            temp[i] = 0;
        }

        Map<Coordinate, Cell> map = gameboard;
        for (Coordinate coord : map.keySet()) {
            temp[coord.getYPos()]++;
        }

        for (int i = 0; i < temp.length; i++) {
            if (temp[i] == this.width) {
                lines.add(i);
            }

        }
        return lines;
    }
}
