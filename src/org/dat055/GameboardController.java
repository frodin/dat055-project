package org.dat055;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;

public class GameboardController {
    private Gameboard gameboard;

    public GameboardController(int width, int height) {
        this.gameboard = new Gameboard(width, height);
    }

    public Coordinate getTetrominoPosition() {
        //return gameboard.getTetrominoPosition();
        return null;
    }
    public void setTetrominoPosition(int x, int y) {
        //gameboard.setTetrominoPosition(new Coordinate(x, y));
    }
    public void tick() {

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


        //gameboard.createTetromino();
    }
    public Gameboard getGameboard() {
        return this.gameboard;
    }
}
