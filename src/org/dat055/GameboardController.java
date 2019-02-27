package org.dat055;

import javafx.application.Platform;
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

public class GameboardController extends Observable {
    private Timer tickTimer;
    private Gameboard gameboard;

    public GameboardController(int width, int height) {
        this.gameboard = new Gameboard(width, height);
        tickTimer = new Timer();
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
        setChanged();
        notifyObservers();

    }

    public boolean haveWeLost(){

        if ( getTetrominoCells() == null){
            System.out.println(" THis triggered");
        }
        for ( Map.Entry<Coordinate, Cell> tetroRef : getTetrominoCells().entrySet() ){
            int tetroX = tetroRef.getKey().getXPos();
            int tetroY = tetroRef.getKey().getYPos();
               if(gameboard.getCell(tetroX, tetroY) != null) {
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
        setChanged();
        notifyObservers();
    }

    public void stopTimer(){
        tickTimer.cancel();
    }
    public void rotateTetromino(){
        gameboard.rotateTetromino();
    }


}


