package org.dat055;

import java.util.ArrayList;
import java.util.HashMap;

public class ActiveTetronimo {
    //ska ha en privat variabel som är en lista över hashmaps innehållande en point(key)
    // med tillhörande cell
    //ska ha ett nuvarande state, privat variable med getter
    //ska ha en rotate()-funktion som ändrar nuvarande state till nästkommande state
    private ArrayList<HashMap<Coordinate, Cell>> states; //Lista med alla våra states (representerade av hashmaps)
    private HashMap<Coordinate, Cell> state;
    private HashMap<Coordinate, Cell> currentState; //nuvarande state (vilken rotation tetrominon har just nu)
    private int stateIndex;

    public ActiveTetronimo(){
        states = new ArrayList<>();
        state  = new HashMap<Coordinate, Cell>();
        currentState = states.get(0);
        stateIndex = 0;
    }

    public HashMap<Coordinate,Cell> getState(){ //returnerar nuvarande state
        return states.get(stateIndex);
    }
    public void rotate(){ //ger oss nästa state i listan
        stateIndex = stateIndex % 4 + 1;
        currentState = states.get(stateIndex);
    }

}
