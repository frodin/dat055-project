package org.dat055.views;

import javafx.fxml.FXML;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.dat055.Gameboard;

public class GameView {
    @FXML private GridPane field;
    private Gameboard gameBoard;

    public GameView(Gameboard gameBoard) {
        this.gameBoard = gameBoard;
    }

    @FXML
    private void initialize() {
        field = createGameField(this.gameBoard);
    }

    private static GridPane createGameField(Gameboard gameBoard) {
        GridPane grid = new GridPane();
        grid.setHgap(0);
        grid.setVgap(0);
        return grid;
    }
}
