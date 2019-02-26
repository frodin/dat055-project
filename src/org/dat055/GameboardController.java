package org.dat055;

import javafx.application.Platform;

import java.sql.SQLOutput;
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

        // Kollar om vi faktiskt kan gå ner innan vi gör det. Om vi kan det så utför setTetromino...
        if (canWeMoveDown()) {
            setTetrominoPosition(getTetrominoPosition().getXPos(), getTetrominoPosition().getYPos() + 1);
            System.out.println("[DEBUG] New tetromino position: " +
                    this.gameboard.getTetrominoPosition().getXPos() + "," +
                    this.gameboard.getTetrominoPosition().getYPos());
        }

        // check if we have lines to clear
        ArrayList<Integer> linesToClear = checkLines();
        if (linesToClear.size() > 0) {
            clearMultipleLines(linesToClear);
        }


    }

    /**
     * This method tells us if we currently can move our tetromino( in its current state) down or not.
     * @return True if we can move down, false if we cant move down.
     */

    public boolean canWeMoveDown(){

        // Loopa över tetrominons hashMap-värden som existerar
        for(Map.Entry<Coordinate,Cell> tetrominoRef : getTetrominoCells().entrySet()){

            // Om en utav koordinaterna i tetrominon SKULLE HA nått under botten av spelplanen så return false
            if(tetrominoRef.getKey().getYPos() + 1 == getGameboard().getHeight())
                return false;

            // Loopa över gameBoardets hashMap-värden som existerar
            for(Map.Entry<Coordinate,Cell> gameBoardRef : gameboard.getGameBoard().entrySet()) {

                // Kollar om tetrominons FRAMTIDA y-koordinat matchar någon av gameBoardets existerande y-koordinater
                // Dock ej sina egna koordinater. (Tetrominon kan inte krocka med sig själv)
                if ((tetrominoRef.getKey().getYPos() + 1 == gameBoardRef.getKey().getYPos() && tetrominoRef.getKey().getXPos() == gameBoardRef.getKey().getXPos())&&
                        !(tetrominoRef.hashCode() == gameBoardRef.hashCode())) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * This method tells us if we currently can move our tetromino( in its current state) left or not.
     * @return True if we can move left, false if we cant move left.
     */

    public boolean canWeMoveLeft(){

        // Loopa över tetrominons hashMap-värden som existerar
        for(Map.Entry<Coordinate,Cell> tetrominoRef : getTetrominoCells().entrySet()){

            // Om en utav koordinaterna i tetrominon SKULLE HA nått till vänster om spelplanen så return false
            if(tetrominoRef.getKey().getXPos() - 1 < 0)
                return false;

            // Loopa över gameBoardets hashMap-värden som existerar
            for(Map.Entry<Coordinate,Cell> gameBoardRef : gameboard.getGameBoard().entrySet()) {

                // Kollar om tetrominons FRAMTIDA vänsta x-koordinat matchar någon av gameBoardets existerande x-koordinater
                // Dock ej sina egna koordinater. (Tetrominon kan inte krocka med sig själv)
                if (tetrominoRef.getKey().getXPos() - 1 < gameBoardRef.getKey().getXPos() &&
                        !(tetrominoRef.hashCode() == gameBoardRef.hashCode())) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * This method tells us if we currently can move our tetromino( in its current state) right or not.
     * @return True if we can move right, false if we cant move right.
     */

    public boolean canWeMoveRight(){

        // Loopa över tetrominons hashMap-värden som existerar
        for(Map.Entry<Coordinate,Cell> tetrominoRef : getTetrominoCells().entrySet()){

            // Om en utav koordinaterna i tetrominon SKULLE HA nått till höger om spelplanen så return false
            if(tetrominoRef.getKey().getXPos() + 1 > gameboard.getWidth())
                return false;

            // Loopa över gameBoardets hashMap-värden som existerar
            for(Map.Entry<Coordinate,Cell> gameBoardRef : gameboard.getGameBoard().entrySet()) {

                // Kollar om tetrominons FRAMTIDA högra x-koordinat matchar någon av gameBoardets existerande x-koordinater
                // Dock ej sina egna koordinater. (Tetrominon kan inte krocka med sig själv)
                if (tetrominoRef.getKey().getXPos() + 1 > gameBoardRef.getKey().getXPos() &&
                        !(tetrominoRef.hashCode() == gameBoardRef.hashCode())) {
                    return false;
                }
            }
        }
        return true;
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


