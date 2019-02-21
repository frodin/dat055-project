package org.dat055.views;

import javafx.fxml.FXML;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.dat055.Cell;
import org.dat055.Gameboard;
import org.dat055.GameboardController;

public class GameView {
    @FXML private GridPane field;
    private GameboardController gameBoardController;

    // Width & height of field cells in pixels
    private final static int RECT_WIDTH = 20;
    private final static int RECT_HEIGHT = 20;

    /**
     * Constructor.
     * @param gameBoardController The associated Gameboard model.
     */
    public GameView(GameboardController gameBoardController) {
        this.gameBoardController = gameBoardController;
    }

    @FXML
    private void initialize() {

        this.field.setHgap(5);
        this.field.setVgap(5);

        // Add columns
        for (int i = 0; i < gameBoardController.getGameboard().getWidth(); i++) {
            this.field.getColumnConstraints().add(i,
                    new ColumnConstraints(RECT_WIDTH));
        }

        // Add rows
        for (int j = 0; j < gameBoardController.getGameboard().getHeight(); j++) {
            this.field.getRowConstraints().add(j,
                    new RowConstraints(RECT_HEIGHT));
        }

        //this.field.setGridLinesVisible(true);

        this.gameBoardController.getGameboard().createTetromino();
        System.out.println(this.gameBoardController.getGameboard().getTetrominoCells());
        this.gameBoardController.getGameboard().killTetromino();


        updateField();
    }

    /**
     * Redraws the game field.
     */
    private void updateField() {
        // Loop over gameBoardController to find all cells
        for (int i = 0; i < this.gameBoardController.getGameboard().getWidth(); i++) {
            for (int j = 0; j < this.gameBoardController.getGameboard().getHeight(); j ++) {
                Cell cell = this.gameBoardController.getGameboard().getCell(i, j);

                // Create rectangle
                Rectangle rect = new Rectangle(RECT_WIDTH, RECT_HEIGHT);

                if (cell != null) {
                    // Cell found, get color from cell
                    rect.setFill(Color.RED);
                } else {
                    // No cell found, set color to black
                    rect.setFill(Color.web("111111"));
                }

                // Add rectangle
                this.field.add(rect, i, j);

            }
        }
    }

    /**
     * Takes a Gameboard and returns a drawable GridPane component.
     * @param gameBoard The Gameboard to draw.
     * @return A GridPane based on the Gameboard.
     */
    private static GridPane createGameField(Gameboard gameBoard) {
        GridPane grid = new GridPane();

        return grid;
    }
}
