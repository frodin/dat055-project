package org.dat055.model;

import java.util.ArrayList;

/**
 * Activetetromino is the current tetromino on the gameboard
 * Which contains an arraylist of all its states
 *
 * @author Albin Becevic
 * @version 2019-02-27
 */
public class ActiveTetromino {
    protected ArrayList<State> states;
    private int stateIndex;

    /**
     * Constructor
     * Initiates our instance variables
     */
    public ActiveTetromino() {
        states = new ArrayList<>();
        stateIndex = 0;
    }

    /** Returns the state which stateIndex is currently "pointing" at.
     * @return returns the current state
     */
    public State getState() {
        return states.get(stateIndex);
    }

    /** Returns the next state in line of all available states.
     *  Loops back to the first state if the last state is reached.
     * @return returns the next state
     */
    public State getNextState() {
        return states.get((stateIndex + 1) % this.states.size());

    }

    /**
     * Rotatates to next state
     * At last state go back to the first
     */
    public void rotate() {
        stateIndex = ++stateIndex % this.states.size();
    }
}
