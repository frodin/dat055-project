package org.dat055.model;

import java.util.*;

/**
 * Model of our gameboard consists of a hashmap
 * with coordinate as key and a cell as value
 *
 * @author Dara Khadjehnouri
 * @version 2019-02-27
 */
public class Gameboard extends Observable implements GameBoardInterface {
    private HashMap<Coordinate, Cell> gameboard;
    private Coordinate tetrominoPosition;
    private int width, height;
    private ActiveTetromino activeTetromino;
    private int gameLevel;

    /**
     * Creates an gameboard with a width and a height
     *
     * @param width  in terms of cells
     * @param height in terms of cells
     */
    public Gameboard(int width, int height){
       this.width = width;
       this.height = height;
       gameboard = new HashMap<>();
       gameLevel = 1;
    }

    /**
     * Returns the current integer width of this Gameboard
     * @return width
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * Returns the current integer width of this Gameboard
     * @return height
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * Returns the cell with the coordinate specified with parameter "coord" of this Gameboard.
     * @param coord the coordinate of the cell
     * @return the actual cell returned. returns null if no cell exist
     */
    public Cell getCell(Coordinate coord) {
        return gameboard.get(coord);
    }

    /**
     * Returns the whole HashMap of this Gameboard
     * @return hashMap
     */
    public HashMap<Coordinate, Cell> getGameBoard() {
        return this.gameboard;
    }

    /**
     * Returns the cell with the respective x-position and y-position of this gameboard
     * @param xPos the specified x-position of the cell
     * @param yPos the specified y-position of the cell
     * @return the actual cell returned. Returns null if no cell exist.
     */
    public Cell getCell(int xPos, int yPos) {
        for (Map.Entry<Coordinate, Cell> cell : this.gameboard.entrySet()) {
            if (cell.getKey().getXPos() == xPos && cell.getKey().getYPos() == yPos) {
                return cell.getValue();
            }
        }
        return null;
    }

    /**
     * Puts a specified cell on the gameboard on the coord position. Also notifies Observers
     * @param coord the position to place the cell
     * @param cell the cell to be placed
     */
    public void setCell(Coordinate coord, Cell cell) {
        gameboard.put(coord, cell);
        setChanged();
        notifyObservers();
    }

    /**
     * Puts a specified cell on the gameboard on the x-position and y-position. Also notifies Observers
     * @param xPos the x-position of the cell
     * @param yPos the y-position of the cell
     * @param cell the cell to be placed
     */
    public void setCell(int xPos, int yPos, Cell cell) {
        gameboard.put(new Coordinate(xPos, yPos), cell);
        setChanged();
        notifyObservers();
    }

    /**
     * Removes a cell on the specified coordinate(coord). Also notifies Observers
     * @param coord the coordinate to have its cell removed
     */
    public void removeCell(Coordinate coord) {
        gameboard.remove(coord);
        setChanged();
        notifyObservers();
    }

    /**
     * Removes a cell on the specified x-position and y-position. Also notifies Observers
     * @param xPos the x-position of the cell
     * @param yPos the y-position of the cell
     */
    public void removeCell(int xPos, int yPos) {
        gameboard.remove(new Coordinate(xPos, yPos));
        setChanged();
        notifyObservers();
    }

    /**
     * Returns the Coordinate(tetrominoPosition) of the current tetromino
     * @return coordinate of the tetromino
     */
    public Coordinate getTetrominoPosition() {
        return this.tetrominoPosition;
    }

    /**
     * Sets the coordinate of the tetromino to the specified coordinate. Also notifies Observers
     * @param coordinate the coordinate for the tetromino to be set.
     */
    public void setTetrominoPosition(Coordinate coordinate) {
        tetrominoPosition = coordinate;
        setChanged();
        notifyObservers();
    }

    /**
     * This method returns all cells in the activeTetromino but with new coordinates related to the gameboard.
     * Now coordinates range from 10x20 and before it was 3x3
     *
     * @return Hashmap of actvieTetromino cells and coords but modified to gameboard coordinates
     */
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

    /**
     * @return the next cells and coordinates of the next state of the activeTetromino
     */
    public HashMap<Coordinate, Cell> getNextTetrominoCells() {
        HashMap<Coordinate, Cell> tempHashMap = new HashMap<>();
        for (Map.Entry<Coordinate, Cell> entry : activeTetromino.getNextState().getHashMap().entrySet()) {
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
        //getTetrominoCells();
    }

    /**
     * Creates a tetromino and its states by creating a new object
     * of a random tetromino.
     *
     * @param i integer that is randomized another method
     */
    public void createTetromino(int i) {
        tetrominoPosition = new Coordinate(4, 0);
        switch (i) {
            case 0:
                activeTetromino = new TetrominoI();
                break;
            case 1:
                activeTetromino = new TetrominoJ();
                break;
            case 2:
                activeTetromino = new TetrominoL();
                break;
            case 3:
                activeTetromino = new TetrominoO();
                break;
            case 4:
                activeTetromino = new TetrominoS();
                break;
            case 5:
                activeTetromino = new TetrominoT();
                break;
            case 6:
                activeTetromino = new TetrominoZ();
                break;
        }
        setChanged();
        notifyObservers();
    }

    /**
     * This method creates the random integer and calls the
     * createTetromino method above to make a random choice of tetrominos
     */
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
            //setChanged();
            //notifyObservers();
        }

        // Might not be neccesary, but "disables" the activeTetromino
        activeTetromino = null;
        setChanged();
        notifyObservers();

    }

    /**
     * Increments the gameLevel variable with +1.
     */
    public void levelUp(){
        gameLevel++;
    }

    /**
     * Returns the current integer gamelevel
     * @return gameLevel
     */
    public int getLevel(){
       return gameLevel;
    }

}
