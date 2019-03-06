package org.dat055;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Activetetromino is the current tetromino on the gameboard
 * Which contains an arraylist of all its states
 *
 * @author
 * @version 2019-02-27
 */
public class ActiveTetromino {
    protected ArrayList<State> states;
    private int stateIndex;

    /**
     * Constructor
     * Initiates our instance variables
     */
    public ActiveTetromino(){
        states = new ArrayList<>();
        stateIndex = 0;
    }

    /**
     * @return returns the current state
     */
    public State getState(){
        return states.get(stateIndex);
    }

    /**
     *
     * @return returns the next state
     */
    public State getNextState(){
        return states.get((stateIndex + 1) % this.states.size());

    }

    /**
     * Rotatates to next state
     * At last state go back to the first
     */
    public void rotate(){
        stateIndex = ++stateIndex % this.states.size();
    }
}
