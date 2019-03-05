package org.dat055.test;

import org.dat055.Coordinate;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CoordinateTest {

    @Test
    void getXPos() {
        Coordinate coord = new Coordinate(5, 10);
        assertEquals(5, coord.getXPos());
    }

    @Test
    void getYPos() {
        Coordinate coord = new Coordinate(5, 10);
        assertEquals(10, coord.getYPos());
    }

    @Test
    void equals() {
        Coordinate coord1 = new Coordinate(7, 2);
        Coordinate coord2 = new Coordinate(7, 2);
        Coordinate coord3 = new Coordinate(2, 7);

        // coord 1 == coord2
        assertTrue(coord1.equals(coord2));
        assertTrue(coord2.equals(coord1));

        // coord1 != coord2
        assertFalse(coord1.equals(coord3));
        assertFalse(coord3.equals(coord1));
    }
}