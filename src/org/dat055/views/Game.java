package org.dat055.views;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import org.dat055.Gameboard;

public class Game {
    @FXML private GridPane board;

    // todo: hur ska denna kopplas till gameboard?

    @FXML
    private void initialize() {
        board.setHgap(0);
        board.setVgap(0);

        board.add(new Button(), 0, 0);
        board.add(new Button(), 1, 1);
        board.add(new Button(), 2, 2);

        // add columns to grid
        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setFillWidth(true);
        columnConstraints.setHgrow(Priority.ALWAYS);
        // todo: det ska nog finnas gameBoard.y RowConstraints och gameBoard.x ColumnConstraints
        board.getColumnConstraints().add(columnConstraints);
    }
}
