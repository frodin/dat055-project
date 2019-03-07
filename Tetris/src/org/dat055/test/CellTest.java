package org.dat055.test;

import org.dat055.model.Cell;

import static org.junit.jupiter.api.Assertions.*;

class CellTest {

    private Cell cell;

    @org.junit.jupiter.api.Test
    void getColor() {
        this.cell = new Cell("FF0000");
            assertEquals(this.cell.getColor(), "FF0000");
    }

    @org.junit.jupiter.api.Test
    void setColor() {
        this.cell = new Cell();
        this.cell.setColor("00FF00");
        assertEquals(this.cell.getColor(), "00FF00");
    }
}