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
 *
 *
 * @author
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


    public GameboardController(int width, int height) {
        this.gameboard = new Gameboard(width, height);
        tickTimer = new Timer();
        lost = false;
        this.width = width;
        this.height = height;
    }

    public void resetGameBoardController(){
        tickTimer.cancel();
        tickTimer.purge();
        score = 0;
        this.gameboard = new Gameboard(width, height);
        lost = false;
    }


    public boolean getLost(){
        return this.lost;
    }

    public Coordinate getTetrominoPosition() {
        return gameboard.getTetrominoPosition();
    }

    public void setTetrominoPosition(int x, int y) {
        gameboard.setTetrominoPosition(new Coordinate(x, y));
        setChanged();
        notifyObservers();
    }

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

    public void pause(){
        tickTimer.cancel();
    }

    // move the active tetromino down one block

    public void tick() {
        // Kollar om vi faktiskt kan gå ner innan vi gör det. Om vi kan det så utför setTetromino...
        if (this.getTetrominoCells() != null && this.canMove('d')) {
            setTetrominoPosition(getTetrominoPosition().getXPos(), getTetrominoPosition().getYPos() + 1);
            System.out.println("[DEBUG] New tetromino position: " +
                    this.gameboard.getTetrominoPosition().getXPos() + "," +
                    this.gameboard.getTetrominoPosition().getYPos());
        }
        else{
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
            case 1: this.score += 40; break;
            case 2: this.score += 100; break;
            case 3: this.score += 300; break;
            case 4: this.score += 1200; break;
        }

        setChanged();
        notifyObservers();
    }

    public int getScore() {
        return this.score;
    }

    public int getClearedLines() {
        return this.clearedLines;
    }

    public boolean haveWeLost(){

        if (getTetrominoCells() == null) return false;

        for ( Map.Entry<Coordinate, Cell> tetroRef : getTetrominoCells().entrySet() ){
               if(gameboard.getCell(tetroRef.getKey()) != null) {
                   return true;
               }
        }
        return false;
    }




    public boolean canMove(char d) {
        for(Map.Entry<Coordinate,Cell> tetroCell : getTetrominoCells().entrySet()){
            switch (d){
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
                default: return false;
            }
        }
        return true;
    }
    public boolean canWeRotate(){
        for(Map.Entry<Coordinate, Cell> nextState : getNextTetrominoCells().entrySet()){
            if(nextState.getKey().getXPos() < 0){
                return false;
            }
            else if(nextState.getKey().getXPos() >= gameboard.getWidth()){
                return false;
            } else if(nextState.getKey().getYPos() >= gameboard.getHeight()){
                return false;
            } else if (gameboard.getCell(nextState.getKey().getXPos(), nextState.getKey().getYPos()) != null){
                return false;
            }

           // else if(nextState.getKey().getXPos() == )
        }
        return true;
    }

    private boolean cellDown(int x, int y) {
        return gameboard.getCell(x, y + 1) == null &&
               (y + 1) < gameboard.getHeight();
    }
    private boolean cellLeft(int x, int y) {
        return gameboard.getCell(x - 1, y) == null &&
                (x - 1) >= 0;
    }
    private boolean cellRight(int x, int y) {
        return gameboard.getCell(x + 1, y) == null &&
                (x + 1) < gameboard.getWidth();
    }

    public Gameboard getGameboard() {
        return this.gameboard;
    }

    public HashMap<Coordinate, Cell> getTetrominoCells() {
        return this.gameboard.getTetrominoCells();
    }
    public HashMap<Coordinate, Cell> getNextTetrominoCells(){
        return this.gameboard.getNextTetrominoCells();
    }


    /**
     * Try to move the active tetromino left.
     */
    public void moveLeft() {
        Coordinate currPos = this.gameboard.getTetrominoPosition();
        // calculate next pos here then move it to that position
        Coordinate nextPos = new Coordinate(currPos.getXPos() - 1,currPos.getYPos());
        gameboard.setTetrominoPosition(nextPos);
        //same for moveRight()
    }

    /**
     * Try to move the active tetromino right.
     */
    public void moveRight() {
        Coordinate currPos = this.gameboard.getTetrominoPosition();
        // calculate next pos here then move it to that position
        Coordinate nextPos = new Coordinate(currPos.getXPos() + 1,currPos.getYPos());
        gameboard.setTetrominoPosition(nextPos);
        //same for moveRight()
    }

    /**
     * Try to move the active tetromino down.
     */
    public void moveDown() {
        Coordinate currPos = this.gameboard.getTetrominoPosition();
        // calculate next pos here then move it to that position
        Coordinate nextPos = new Coordinate(currPos.getXPos(),currPos.getYPos() + 1) ;
        gameboard.setTetrominoPosition(nextPos);
    }

    public void killAndReplaceTetromino(){
        gameboard.killTetromino();
        gameboard.createTetromino();
        lost = haveWeLost();
        setChanged();
        notifyObservers();
    }

    public void rotateTetromino(){
        gameboard.rotateTetromino();
    }






    /**
     * clears any number of lines and lowers above cells
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


