package org.dat055;

import javafx.application.Platform;
import java.util.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;

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
        Class<?>[] possibleClasses = {TetrominoI.class, TetrominoS.class, TetrominoJ.class, TetrominoL.class, TetrominoO.class, TetrominoT.class, TetrominoZ.class};
        Random rand = new Random();

        Class<?> cl = possibleClasses[rand.nextInt(possibleClasses.length)];
        try {
            Class.forName(cl.toString());
            Constructor<?> constr = cl.getConstructor(cl);
            Object obj = constr.newInstance(new Object[] { "FF0000" });
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }

        setChanged();
        notifyObservers();
    }

    public Gameboard getGameboard() {
        return this.gameboard;
    }

    public HashMap<Coordinate, Cell> getTetrominoCells() {
        return this.gameboard.getTetrominoCells();
    }

}


