package org.dat055.test;

import org.dat055.model.Coordinate;
import org.dat055.model.State;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StateTest {

    @Test
    void getHashMap() {
        State state = new State(
                "0000FF",
                new Coordinate(0,0),
                new Coordinate(1,1),
                new Coordinate(2,2),
                new Coordinate(3,3)
        );

        // check that the resulting hashmap has 4 elements
        assertEquals(4, state.getHashMap().size());
    }
}