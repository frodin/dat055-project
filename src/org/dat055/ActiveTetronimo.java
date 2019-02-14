package org.dat055;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * ska ha en privat variabel som är en lista över hashmaps innehållande en point(key)
 * med tillhörande cell
 * ska ha ett nuvarande state, privat variable med getter
 * ska ha en rotate()-funktion som ändrar nuvarande state till nästkommande state
 */
public class ActiveTetronimo {
    private ArrayList<HashMap<Coordinate, Cell>> states; //List of all our diffrent tetromino states (represented by hashmaps)
    private HashMap<Coordinate, Cell> state;
    private HashMap<Coordinate, Cell> currentState; //Which rotationen the current tetromino has
    private int stateIndex;

    /**
     * Initiates our instance variables
     */
    public ActiveTetronimo(){
        states = new ArrayList<>();
        state  = new HashMap<Coordinate, Cell>();
        currentState = states.get(0);
        stateIndex = 0;
    }

    public HashMap<Coordinate,Cell> getState(){ //return current state
        return currentState;
    }
    public void rotate(){ //give us next state in the list
        stateIndex = stateIndex % 4 + 1;
        if(stateIndex == 4){ //Jump back to zero, prevent the currentState to get out of bounds
            stateIndex = 0;
        }
        currentState = states.get(stateIndex);
    }

}
