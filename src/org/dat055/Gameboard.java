package org.dat055;

import java.util.*;

public class Gameboard implements GameBoardInterface{
    private HashMap<Coordinate, Cell> gameboard;
    private Coordinate tetrominoPosition;
    private int width, height;
    private ActiveTetromino activeTetromino;


    public Gameboard(int width, int height){
       this.width = width;
       this.height = height;
       gameboard = new HashMap<>();
       tetrominoPosition = new Coordinate(4,0);
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


    public HashMap<Coordinate, Cell> getGameBoard(){
        return this.gameboard;
    }
    public Cell getCell(int xPos, int yPos) {
        for (Map.Entry<Coordinate,Cell> cell : this.gameboard.entrySet()) {
            if (cell.getKey().getXPos() == xPos && cell.getKey().getYPos() == yPos) {
                return cell.getValue();
            }
        }
        return null;
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

    public Coordinate getTetrominoPosition(){
        return this.tetrominoPosition;
    }

    public void setTetrominoPosition(Coordinate coordinate){
        tetrominoPosition = coordinate;
    }

    // This method returns all cells in the activeTetromino but with new coordinates related to the gameboard.
    // Now coordinates range from 10x20 and before it was 3x3

    public HashMap<Coordinate,Cell> getTetrominoCells(){
        HashMap<Coordinate,Cell> tempHashMap = new HashMap<Coordinate,Cell>();

        //We iterate over all entries in the active tetromino.(No null values)
        for(Map.Entry<Coordinate,Cell> entry : activeTetromino.getState().getHashMap().entrySet()){

            // Assign the tempHashMap with updated coordinates
            tempHashMap.put(new Coordinate(
                    entry.getKey().getXPos()+ tetrominoPosition.getXPos(),
                    entry.getKey().getYPos() + tetrominoPosition.getYPos()),
                    entry.getValue());
        }

        return tempHashMap;
    }

    /**
     * Rotates the current tetromino
     */
    public void rotateTetromino(){
        activeTetromino.rotate();
    }

    public void createTetromino(int i){
        switch(i){
            case 0: activeTetromino = new TetrominoI();
            break;
            case 1: activeTetromino = new TetrominoJ();
            break;
            case 2: activeTetromino = new TetrominoL();
            break;
            case 3: activeTetromino = new TetrominoO();
            break;
            case 4: activeTetromino = new TetrominoS();
            break;
            case 5: activeTetromino = new TetrominoT();
            break;
            case 6: activeTetromino = new TetrominoZ();
            break;
        }
    }

    public void createTetromino(){
        Random rand = new Random();
        int random = rand.nextInt(7);
        createTetromino(random);
    }

    /**
     * Merges the given tetromino with the gameboard and "disables" the activeTetromino so it no longer exists.
     */
    public void killTetromino(){

        for(Map.Entry<Coordinate,Cell> entry : activeTetromino.getState().getHashMap().entrySet()){
            setCell(entry.getKey(), entry.getValue());
        }
        // Might not be neccesary, but "disables" the activeTetromino
        activeTetromino = null;

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
