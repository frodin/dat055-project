package org.dat055;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * ska ha en privat variabel som är en lista över hashmaps innehållande en point(key)
 * med tillhörande cell
 * ska ha ett nuvarande state, privat variable med getter
 * ska ha en rotate()-funktion som ändrar nuvarande state till nästkommande state
 */
public class ActiveTetromino {
    private ArrayList<State> states; //List of all our diffrent tetromino states (represented by hashmaps)
    private State currentState; //Which rotationen the current tetromino has
    private int stateIndex;

    /**
     * Initiates our instance variables
     */
    public ActiveTetromino(){
        states = new ArrayList<>();
        currentState = states.get(0);
        stateIndex = 0;
    }

    public State getState(){ //return current state
        return currentState;
    }

    public void rotate(){ //give us next state in the list
        stateIndex = stateIndex++ % 4;
        currentState = states.get(stateIndex);
    }

}
