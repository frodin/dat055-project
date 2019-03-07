package org.dat055;

import java.util.*;

public class Gameboard extends Observable implements GameBoardInterface {
    private HashMap<Coordinate, Cell> gameboard;
    private Coordinate tetrominoPosition;
    private int width, height;
    private ActiveTetromino activeTetromino;
    private int gameLevel;


    public Gameboard(int width, int height){
       this.width = width;
       this.height = height;
       gameboard = new HashMap<>();
       gameLevel = 1;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public Cell getCell(Coordinate coord) {
        return gameboard.get(coord);
    }


    public HashMap<Coordinate, Cell> getGameBoard() {
        return this.gameboard;
    }

    public Cell getCell(int xPos, int yPos) {
        for (Map.Entry<Coordinate, Cell> cell : this.gameboard.entrySet()) {
            if (cell.getKey().getXPos() == xPos && cell.getKey().getYPos() == yPos) {
                return cell.getValue();
            }
        }
        return null;
    }

    public void setCell(Coordinate coord, Cell cell) {
        gameboard.put(coord, cell);
        setChanged();
        notifyObservers();
    }

    public void setCell(int xPos, int yPos, Cell cell) {
        gameboard.put(new Coordinate(xPos, yPos), cell);
        setChanged();
        notifyObservers();
    }

    public void removeCell(Coordinate coord) {
        gameboard.remove(coord);
        setChanged();
        notifyObservers();
    }

    public void removeCell(int xPos, int yPos) {
        gameboard.remove(new Coordinate(xPos, yPos));
        setChanged();
        notifyObservers();
    }

    public Coordinate getTetrominoPosition() {
        return this.tetrominoPosition;
    }

    public void setTetrominoPosition(Coordinate coordinate) {
        tetrominoPosition = coordinate;
        setChanged();
        notifyObservers();
    }

    // This method returns all cells in the activeTetromino but with new coordinates related to the gameboard.
    // Now coordinates range from 10x20 and before it was 3x3

    public HashMap<Coordinate, Cell> getTetrominoCells() {
        // Return null if no active tetromino exists
        if (this.activeTetromino == null)
            return null;

        HashMap<Coordinate, Cell> tempHashMap = new HashMap<Coordinate, Cell>();

        //We iterate over all entries in the active tetromino.(No null values)
        for (Map.Entry<Coordinate, Cell> entry : activeTetromino.getState().getHashMap().entrySet()) {

            // Assign the tempHashMap with updated coordinates
            tempHashMap.put(new Coordinate(
                            entry.getKey().getXPos() + tetrominoPosition.getXPos(),
                            entry.getKey().getYPos() + tetrominoPosition.getYPos()),
                    entry.getValue());
        }

        return tempHashMap;
    }
    public HashMap<Coordinate, Cell> getNextTetrominoCells(){
        HashMap<Coordinate, Cell> tempHashMap = new HashMap<>();
        for(Map.Entry<Coordinate, Cell> entry : activeTetromino.getNextState().getHashMap().entrySet()){
            tempHashMap.put(new Coordinate(
                    entry.getKey().getXPos() + tetrominoPosition.getXPos(),
                    entry.getKey().getYPos() + tetrominoPosition.getYPos()), entry.getValue());
        }
        return tempHashMap;
    }

    /**
     * Rotates the current tetromino
     */
    public void rotateTetromino() {
        activeTetromino.rotate();
        setChanged();
        notifyObservers();
    }

    public void createTetromino(int i){
        tetrominoPosition = new Coordinate(4,0);
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
        setChanged();
        notifyObservers();
    }

    public void createTetromino() {
        Random rand = new Random();
        int random = rand.nextInt(7);
        createTetromino(random);
    }

    /**
     * Merges the given tetromino with the gameboard and "disables" the activeTetromino so it no longer exists.
     */
    public void killTetromino() {
        // Null check
        if (this.activeTetromino == null)
            return;

        for (Map.Entry<Coordinate, Cell> cell : getTetrominoCells().entrySet()) {
            setCell(cell.getKey(), cell.getValue());
        }

        // Disables the activeTetromino
        activeTetromino = null;
        setChanged();
        notifyObservers();

    }

    public void levelUp(){
        gameLevel++;
    }

    public int getLevel(){
       return gameLevel;
    }

}
