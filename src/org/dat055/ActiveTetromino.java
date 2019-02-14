package org.dat055;

import java.util.ArrayList;
import java.util.HashMap;

public class ActiveTetromino {
    protected ArrayList<State> states;
    protected State currentState;
    private int stateIndex;

    /**
     * Constructor
     * Initiates our instance variables
     */
    public ActiveTetromino(){
        states = new ArrayList<>();
        currentState = states.get(0);
        stateIndex = 0;
    }

    /**
     * @return returns currentState
     */
    public State getState(){
        return currentState;
    }

    /**
     * Rotatates to next state
     * At last state go back to the first
     */
    public void rotate(){
        stateIndex = stateIndex++ % this.states.size();
        currentState = states.get(stateIndex);
    }

}
