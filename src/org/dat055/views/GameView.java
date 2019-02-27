package org.dat055.views;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import org.dat055.Cell;
import org.dat055.Coordinate;
import org.dat055.Gameboard;
import org.dat055.GameboardController;

import java.util.Map;
import java.util.Observable;
import java.util.Observer;

public class GameView implements Observer {
    @FXML private GridPane field;
    private GameboardController gameBoardController;

    // fps counter
    @FXML private Label fpsCounter;
    private final long[] frameTimes = new long[100];
    private int frameTimeIndex = 0;
    private boolean arrayFilled = false;

    // redraw counter
    @FXML private Label redrawCounter;
    private int redraws = 0;

    // change event counter
    @FXML private Label changeCounter;
    private int changeEvents = 0;

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

        this.gameBoardController.getGameboard().addObserver(this);
        this.gameBoardController.getGameboard().createTetromino();

        // fps counter
        AnimationTimer frameRateMeter = new AnimationTimer() {
            @Override
            public void handle(long now) {
                long oldFrameTime = frameTimes[frameTimeIndex] ;
                frameTimes[frameTimeIndex] = now ;
                frameTimeIndex = (frameTimeIndex + 1) % frameTimes.length ;
                if (frameTimeIndex == 0) {
                    arrayFilled = true;
                }
                if (arrayFilled) {
                    long elapsedNanos = now - oldFrameTime ;
                    long elapsedNanosPerFrame = elapsedNanos / frameTimes.length ;
                    double frameRate = 1_000_000_000.0 / elapsedNanosPerFrame ;
                    fpsCounter.setText(String.format("[FPS: %.3f]", frameRate));
                }
            }
        };

        updateField();
    }

    /**
     * Redraws the game field.
     */
    public void updateField() {
        // Loop over game board to draw all cells
        for (int i = 0; i < this.gameBoardController.getGameboard().getWidth(); i++) {
            for (int j = 0; j < this.gameBoardController.getGameboard().getHeight(); j ++) {
                Cell cell = this.gameBoardController.getGameboard().getCell(i, j);

                // Create rectangle
                Rectangle rect = new Rectangle(RECT_WIDTH, RECT_HEIGHT);

                if (cell != null) {
                    // Cell found, get color from cell
                    rect.setFill(Color.web(cell.getColor()));
                } else {
                    // No cell found, set color to black
                    rect.setFill(Color.web("111111"));
                }

                // Add rectangle
                this.field.add(rect, i, j);

            }
        }

        // Draw active tetronimo
        for (Map.Entry<Coordinate, Cell> entry : this.gameBoardController.getTetrominoCells().entrySet()) {
            Rectangle rect = new Rectangle(RECT_WIDTH, RECT_HEIGHT);
            rect.setFill(Color.web(entry.getValue().getColor()));
            this.field.add(rect, entry.getKey().getXPos(), entry.getKey().getYPos());
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
        // Update draw counter
        this.redraws++;
        this.redrawCounter.setText(" [Redraws: " + this.redraws + "]");
    }

    @Override
    public void update(Observable obj, Object arg) {
        this.changeEvents++;
        this.changeCounter.setText(" [Change events: " + this.changeEvents + "]");
        System.out.println("[DEBUG] Change detected. Changes: " + this.changeEvents);
        updateField();
    }
}
