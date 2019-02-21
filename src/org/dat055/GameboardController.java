package org.dat055;

import javafx.application.Platform;

import java.util.*;

public class GameboardController extends Observable {
    private Gameboard gameboard;

    public GameboardController(int width, int height) {
        this.gameboard = new Gameboard(width, height);
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
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                Platform.runLater(() -> {
                    tick();
                });
            }
        }, 1000, 1000);

    }
    public void tick() {
        // move the active tetromino down one block
        setTetrominoPosition(getTetrominoPosition().getXPos(), getTetrominoPosition().getYPos()+1);
        System.out.println("[DEBUG] New tetromino position: " +
                this.gameboard.getTetrominoPosition().getXPos() + "," +
                this.gameboard.getTetrominoPosition().getYPos());
    }
    public void createRandomTetromino() {
        gameboard.createTetromino();
        notifyObservers();
    }
    public Gameboard getGameboard() {
        return this.gameboard;
    }

    public HashMap<Coordinate, Cell> getTetrominoCells() {
        return this.gameboard.getTetrominoCells();
    }

}


