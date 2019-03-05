package org.dat055.views;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.dat055.*;

import java.io.IOException;
import java.io.File;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

public class GameView implements Observer {
    @FXML private GridPane field;
    private GameboardController gameBoardController;
    private MediaPlayer mediaPlayer;

    // score counter
    @FXML private HBox scoreArea;
    @FXML private Label scoreLabel;
    @FXML private Label scoreCountLabel;

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

        // start music
        String musicFile = "tetris.mp3";
        Media sound = new Media(new File(musicFile).toURI().toString());
        mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();


        // start game
        this.gameBoardController.getGameboard().addObserver(this);
        this.gameBoardController.getGameboard().createTetromino();
        this.gameBoardController.start();

        // fps counter
        fpsCounter.setText("[FPS: ??.???]");
        AnimationTimer frameRateMeter = new AnimationTimer() {
            @Override
            public void handle(long now) {
                long oldFrameTime = frameTimes[frameTimeIndex];
                frameTimes[frameTimeIndex] = now;
                frameTimeIndex = (frameTimeIndex + 1) % frameTimes.length;
                if (frameTimeIndex == 0) {
                    arrayFilled = true;
                }
                if (arrayFilled) {
                    long elapsedNanos = now - oldFrameTime;
                    long elapsedNanosPerFrame = elapsedNanos / frameTimes.length;
                    double frameRate = 1_000_000_000.0 / elapsedNanosPerFrame;
                    fpsCounter.setText(String.format("[FPS: %.3f]", frameRate));
                }
            }
        };
        frameRateMeter.start();

        this.initializeField();

        // align and initialize score counter label
        this.scoreArea.setSpacing(5);
        this.scoreCountLabel.setText(Integer.toString(0));
        scoreArea.setAlignment(Pos.BOTTOM_CENTER);
        scoreArea.setPadding(new Insets(0, 0, 0, -field.getMaxWidth()));
    }

    /**
     * Draw the whole gamefield including empty cells.
     * Only run once.
     */
    public void initializeField() {
        // Draw the initial gameboard, initially only contains empty (black) cells
        for (int i = 0; i < this.gameBoardController.getGameboard().getWidth(); i++) {
            for (int j = 0; j < this.gameBoardController.getGameboard().getHeight(); j ++) {
                Cell cell = this.gameBoardController.getGameboard().getCell(i, j);
                Rectangle rect = new Rectangle(RECT_WIDTH, RECT_HEIGHT);
                rect.setFill(Color.web("111111"));
                this.field.add(rect, i, j);
            }
        }
        field.setMaxWidth(RECT_WIDTH*this.gameBoardController.getGameboard().getWidth());
        field.setMaxHeight(RECT_HEIGHT*this.gameBoardController.getGameboard().getHeight());
    }

    /**
     * Helper function: return a Rectangle object from the game field
     */
    private Rectangle getNode(int x, int y) {
        for (Node node : field.getChildren()) {
            if (GridPane.getColumnIndex(node) == x && GridPane.getRowIndex(node) == y) {
                return (Rectangle) node;
            }
        }
        return null;
    }

    /**
     * Redraws the game field.
     */
    public void updateField() {
        // Loop over all nodes in GridPane
        for (Node node : field.getChildren()) {
            Rectangle rect = (Rectangle) node;
            // Look for a corresponding Cell object in Gameboard
            Cell cell = this.gameBoardController.getGameboard().getCell(
                    GridPane.getColumnIndex(node),
                    GridPane.getRowIndex(node)
            );
            // If such a cell exists, get the color from that cell. Otherwise paint it black.
            if (cell != null) {
                rect.setFill(Color.web(cell.getColor()));
            } else {
                rect.setFill(Color.web("111111"));
            }
        }

        // Draw active tetronimo
        if (this.gameBoardController.getTetrominoCells() != null) {
            for (Map.Entry<Coordinate, Cell> entry : this.gameBoardController.getTetrominoCells().entrySet()) {
                Rectangle rect = getNode(entry.getKey().getXPos(), entry.getKey().getYPos());

                if (rect != null)
                    rect.setFill(Color.web(entry.getValue().getColor()));
            }
        }

        // Update draw counter
        this.redraws++;
        this.redrawCounter.setText(" [Redraws: " + this.redraws + "]");

        // Update score counter
        this.scoreCountLabel.setText(String.format("%d", this.gameBoardController.getScore()));
    }

    @Override
    public void update(Observable obj, Object arg) {
        this.changeEvents++;
        this.changeCounter.setText(" [Change events: " + this.changeEvents + "]");
        System.out.println("[DEBUG] Change detected. Changes: " + this.changeEvents);
        if(gameBoardController.getLost()){ // Check if we have lost after the a new tetromino has been created.
            System.out.println("you lost!!!!!");
            gameBoardController.resetGameBoardController();
            mediaPlayer.stop();
            Stage s = Main.getPrimaryStage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("menu_view.fxml"));
            loader.setController(new MenuView(this.gameBoardController));
            try {
                s.setScene(new Scene(loader.load()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        updateField();

    }
}
