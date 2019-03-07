package org.dat055;

import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleBooleanProperty;
import org.dat055.Gameboard;


import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLOutput;
import java.util.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;
import java.util.Timer;

/**
 * The Controller part of the program
 *
 * @author Thomas Frödin Larsson
 * @version 2019-03-06
 */
public class GameboardController extends Observable {
    private Timer tickTimer;
    private Gameboard gameboard;
    private boolean lost;
    private int score;
    private int clearedLines;
    private int width;
    private int height;


    /**
     * Constructor
     *
     * @param width  width of gameboard
     * @param height height of gameboard
     */
    public GameboardController(int width, int height) {
        this.gameboard = new Gameboard(width, height);
        tickTimer = new Timer();
        lost = false;
        this.width = width;
        this.height = height;
    }

    /**
     * Run when game is lost in order to start a new game.
     */
    public void resetGameBoardController() {
        tickTimer.cancel();
        tickTimer.purge();
        score = 0;
        this.gameboard = new Gameboard(width, height);
        lost = false;
    }


    /**
     * Getter
     *
     * @return
     */
    public boolean getLost() {
        return this.lost;
    }

    /**
     * Getter of active tetromino
     *
     * @return
     */
    public Coordinate getTetrominoPosition() {
        return gameboard.getTetrominoPosition();
    }

    /**
     * Setter of active tetromino
     *
     * @param x horizontal coordinate
     * @param y vertical coordinate
     */
    public void setTetrominoPosition(int x, int y) {
        gameboard.setTetrominoPosition(new Coordinate(x, y));
        setChanged();
        notifyObservers();
    }

    /**
     * Sets the game in motion by periodically running a task
     */
    public void start() {
        tickTimer = new Timer();
        tickTimer.schedule(new TimerTask() {
            public void run() {
                Platform.runLater(() -> {
                    tick();

                });
            }
        }, 1000, 1000);
    }

    /**
     * The pause function
     */
    public void pause() {
        tickTimer.cancel();
    }

    // move the active tetromino down one block

    /**
     * Performs all checks and actions for every "game tick" as the tetromino
     * moves down.
     */
    public void tick() {
        // Checks if we can move down or not
        if (this.getTetrominoCells() != null && this.canMove('d')) {
            setTetrominoPosition(getTetrominoPosition().getXPos(), getTetrominoPosition().getYPos() + 1);
            System.out.println("[DEBUG] New tetromino position: " +
                    this.gameboard.getTetrominoPosition().getXPos() + "," +
                    this.gameboard.getTetrominoPosition().getYPos());
        } else {
            killAndReplaceTetromino();
        }
        // check if we have lines to clear
        ArrayList<Integer> linesToClear = checkLines();
        if (linesToClear.size() > 0) {
            clearMultipleLines(linesToClear);
        }

        // If we cleared any lines, increase our score and our cleared-lines counter
        int numLines = linesToClear.size();
        this.clearedLines += numLines;
        switch (numLines) {
            case 1:
                this.score += 40;
                break;
            case 2:
                this.score += 100;
                break;
            case 3:
                this.score += 300;
                break;
            case 4:
                this.score += 1200;
                break;
        }

        setChanged();
        notifyObservers();
    }

    /**
     * Getter for score
     *
     * @return current accumulated points
     */
    public int getScore() {
        return this.score;
    }

    /**
     * Checks if we have lost the game
     *
     * @return whether we lost
     */
    public boolean haveWeLost() {

        if (getTetrominoCells() == null) return false;

        for (Map.Entry<Coordinate, Cell> tetroRef : getTetrominoCells().entrySet()) {
            if (gameboard.getCell(tetroRef.getKey()) != null) {
                return true;
            }
        }
        return false;
    }


    /**
     * Checks if we can move in different directions
     *
     * @param d specifies the direction
     * @return whether we can move or not
     */
    public boolean canMove(char d) {
        for (Map.Entry<Coordinate, Cell> tetroCell : getTetrominoCells().entrySet()) {
            switch (d) {
                case 'd':
                    if (!cellDown(tetroCell.getKey().getXPos(), tetroCell.getKey().getYPos())) {
                        return false;
                    }
                    break;
                case 'l':
                    if (!cellLeft(tetroCell.getKey().getXPos(), tetroCell.getKey().getYPos())) {
                        return false;
                    }
                    break;
                case 'r':
                    if (!cellRight(tetroCell.getKey().getXPos(), tetroCell.getKey().getYPos())) {
                        return false;
                    }
                    break;
                default:
                    return false;
            }
        }
        return true;
    }

    /**
     * Checks if the active tetromino can rotate in its current position
     *
     * @return whether it can rotate
     */
    public boolean canWeRotate() {
        for (Map.Entry<Coordinate, Cell> nextState : getNextTetrominoCells().entrySet()) {
            if (nextState.getKey().getXPos() < 0) {
                return false;
            } else if (nextState.getKey().getXPos() >= gameboard.getWidth()) {
                return false;
            } else if (nextState.getKey().getYPos() >= gameboard.getHeight()) {
                return false;
            } else if (gameboard.getCell(nextState.getKey().getXPos(), nextState.getKey().getYPos()) != null) {
                return false;
            }
        }
        return true;
    }

    /**
     * Helper method to check if we can move down
     *
     * @param x horizontal position
     * @param y vertical position
     * @return whether we can move down
     */
    private boolean cellDown(int x, int y) {
        return gameboard.getCell(x, y + 1) == null &&
                (y + 1) < gameboard.getHeight();
    }

    /**
     * Helper method to check if we can move left
     *
     * @param x horizontal position
     * @param y vertical position
     * @return whether we can move left
     */
    private boolean cellLeft(int x, int y) {
        return gameboard.getCell(x - 1, y) == null &&
                (x - 1) >= 0;
    }

    /**
     * Helper method to check if we can move right
     *
     * @param x horizontal position
     * @param y vertical position
     * @return whether we can move right
     */
    private boolean cellRight(int x, int y) {
        return gameboard.getCell(x + 1, y) == null &&
                (x + 1) < gameboard.getWidth();
    }

    /**
     * Getter for gameboard object
     *
     * @return current gameboard in the controller
     */
    public Gameboard getGameboard() {
        return this.gameboard;
    }

    /**
     * Getter for same method in Gameboard class
     *
     * @return position of cells of active tetromino
     */
    public HashMap<Coordinate, Cell> getTetrominoCells() {
        return this.gameboard.getTetrominoCells();
    }

    /**
     * Getter for same method in Gameboard class
     *
     * @return next position of cells of active tetromino
     */
    public HashMap<Coordinate, Cell> getNextTetrominoCells() {
        return this.gameboard.getNextTetrominoCells();
    }


    /**
     * Try to move the active tetromino left.
     */
    public void moveLeft() {
        Coordinate currPos = this.gameboard.getTetrominoPosition();
        // calculate next pos here then move it to that position
        Coordinate nextPos = new Coordinate(currPos.getXPos() - 1, currPos.getYPos());
        gameboard.setTetrominoPosition(nextPos);
        //same for moveRight()
    }

    /**
     * Try to move the active tetromino right.
     */
    public void moveRight() {
        Coordinate currPos = this.gameboard.getTetrominoPosition();
        // calculate next pos here then move it to that position
        Coordinate nextPos = new Coordinate(currPos.getXPos() + 1, currPos.getYPos());
        gameboard.setTetrominoPosition(nextPos);
        //same for moveRight()
    }

    /**
     * Try to move the active tetromino down.
     */
    public void moveDown() {
        Coordinate currPos = this.gameboard.getTetrominoPosition();
        // calculate next pos here then move it to that position
        Coordinate nextPos = new Coordinate(currPos.getXPos(), currPos.getYPos() + 1);
        gameboard.setTetrominoPosition(nextPos);
    }

    /**
     * Deactivates the current active tetromino and creates a new one in its place
     */
    public void killAndReplaceTetromino() {
        gameboard.killTetromino();
        gameboard.createTetromino();
        lost = haveWeLost();
        setChanged();
        notifyObservers();
    }

    /**
     * Rotates the current tetromino
     */
    public void rotateTetromino() {
        gameboard.rotateTetromino();
    }


    /**
     * clears any number of lines and lowers above cells
     *
     * @param y array of rows
     */
    //@Override
    public void clearMultipleLines(ArrayList<Integer> y) {
        Collections.sort(y);
        y.forEach(i -> clearLine(i));
        setChanged();
        notifyObservers();
    }

    /**
     * helper method: clears a line and lowers above cells.
     *
     * @param y specific row
     */
    private void clearLine(int y) {
        deleteRow(y);
        lowerAbove(y);
    }

    private void deleteRow(int y) {
        for (int i = 0; i < gameboard.getWidth(); i++) {
            gameboard.removeCell(i, y);
        }
    }

    /**
     * lowers all cells on all rows above by 1
     *
     * @param y specific row
     */
    private void lowerAbove(int y) {
        for (int i = y - 1; i >= 0; i--) {
            for (int j = 0; j < gameboard.getWidth(); j++) {
                Cell myCell = gameboard.getCell(j, i);
                if (myCell != null) {
                    gameboard.setCell(j, i + 1, myCell);
                    gameboard.removeCell(j, i);
                }
            }
        }
    }

    /**
     * scans the gameboard for lines to clear
     *
     * @return list of rows to clear, will be empty if no lines found.
     */
    //@Override
    public ArrayList<Integer> checkLines() {
        ArrayList<Integer> lines = new ArrayList<>();

        int[] temp = new int[gameboard.getHeight()];
        for (int i = 0; i < temp.length; i++) {
            temp[i] = 0;
        }

        Map<Coordinate, Cell> map = gameboard.getGameBoard();
        for (Coordinate coord : map.keySet()) {
            temp[coord.getYPos()]++;
        }

        for (int i = 0; i < temp.length; i++) {
            if (temp[i] == gameboard.getWidth()) {
                lines.add(i);
            }

        }
        return lines;
    }

}


