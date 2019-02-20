package org.dat055.views;

import javafx.fxml.FXML;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import org.dat055.Cell;
import org.dat055.Gameboard;

public class GameView {
    @FXML private GridPane field;
    private Gameboard gameBoard;

    // Width & height of drawn game field in pixels
    private final static int FIELD_WIDTH_PX = 100;
    private final static int FIELD_HEIGHT_PX = 200;

    /**
     * Constructor.
     * @param gameBoard The associated Gameboard model.
     */
    public GameView(Gameboard gameBoard) {
        this.gameBoard = gameBoard;
    }

    @FXML
    private void initialize() {
        // add a sample cell to gameBoard which should be drawn
        gameBoard.setCell(0, 0, new Cell("FF0000"));

        field = createGameField(this.gameBoard);

        updateField();
    }

    /**
     * Redraws the game field.
     */
    private void updateField() {

    }

    /**
     * Takes a Gameboard and returns a drawable GridPane component.
     * @param gameBoard The Gameboard to draw.
     * @return A GridPane based on the Gameboard.
     */
    private static GridPane createGameField(Gameboard gameBoard) {
        GridPane grid = new GridPane();
        grid.setHgap(0);
        grid.setVgap(0);

        // Add columns
        int columnWidth = FIELD_WIDTH_PX / gameBoard.getWidth();
        for (int i = 0; i < gameBoard.getWidth(); i++) {
            grid.getColumnConstraints().add(i,
                    new ColumnConstraints(columnWidth));
        }

        // Add rows
        int rowHeight = FIELD_HEIGHT_PX/gameBoard.getHeight();
        for (int j = 0; j < gameBoard.getHeight(); j++) {
            grid.getColumnConstraints().add(j,
                    new ColumnConstraints(rowHeight));
        }

        grid.setGridLinesVisible(true);

        return grid;
    }
}
